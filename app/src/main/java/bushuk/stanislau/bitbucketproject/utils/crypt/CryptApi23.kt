package bushuk.stanislau.bitbucketproject.utils.crypt

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.constants.Constants
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject

@TargetApi(Build.VERSION_CODES.M)
class CryptApi23 @Inject constructor(val context: Context) : Crypto {

    private lateinit var keyStore: KeyStore
    private val AndroidKeyStore = Constants.KEY_STORE
    private val KEY_ALIAS = Constants.TOKEN_PREFERENCES
    private val AES_MODE = "AES/GCM/NoPadding"
    var flags = Base64.NO_PADDING

    init {
        App.component.inject(this)
        init()
    }

    private fun init() {
        keyStore = KeyStore.getInstance(AndroidKeyStore)
        keyStore.load(null)

        if (!keyStore.containsAlias(KEY_ALIAS)) {

            val keyGenerator: KeyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, AndroidKeyStore)
            keyGenerator.init(
                    KeyGenParameterSpec.Builder(KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                            .build())

            keyGenerator.generateKey()
        }
    }


    override fun encrypt(byteArray: ByteArray): ByteArray {
        val keyStoreKey = keyStore.getKey(KEY_ALIAS, null)
        val cipher = Cipher.getInstance(AES_MODE)
        cipher.init(Cipher.ENCRYPT_MODE, keyStoreKey)
        val encodedBytes = cipher.doFinal(byteArray)
        val params = cipher.parameters
        val iv = params.getParameterSpec(GCMParameterSpec::class.java).iv
        saveIv(iv)

        return encodedBytes
    }

    override fun decrypt(byteArray: ByteArray): ByteArray {
        val iv = getIv()
        val ivSpec = GCMParameterSpec(128, iv)
        val keyStoreKey = keyStore.getKey(KEY_ALIAS, null) as SecretKey
        val cipher = Cipher.getInstance(AES_MODE)
        cipher.init(Cipher.DECRYPT_MODE, keyStoreKey, ivSpec)

        return cipher.doFinal(byteArray)
    }

    private fun saveIv(iv: ByteArray) {
        val ivString: String = Base64.encodeToString(iv, flags)
        context.getSharedPreferences("IV", Context.MODE_PRIVATE).edit().putString("IV", ivString).apply()
    }

    private fun getIv(): ByteArray {
        val ivString: String = context.getSharedPreferences("IV", Context.MODE_PRIVATE).getString("IV", null)
        return Base64.decode(ivString, flags)
    }

}