package bushuk.stanislau.bitbucketproject

import android.annotation.TargetApi
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import timber.log.Timber
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec


class TokenPreferences(val context: Context) {


    /**
     *
     * NEED HARD REFACTORING, SO SHITY CODE
     *
     */


    private lateinit var keyStore: KeyStore
    private val AndroidKeyStore = Constants.KEY_STRORE
    private val KEY_ALIAS = Constants.TOKEN
    private val AES_MODE = "AES/GCM/NoPadding"
    var flags = Base64.NO_PADDING

    init {
        init()
    }

    @TargetApi(Build.VERSION_CODES.M)
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


    private fun encrypt(initialBytes: ByteArray): ByteArray {
        val keyStoreKey = keyStore.getKey(KEY_ALIAS, null) as SecretKey
        val cipher = Cipher.getInstance(AES_MODE)
        cipher.init(Cipher.ENCRYPT_MODE, keyStoreKey)
        val encodedBytes = cipher.doFinal(initialBytes)
        val params = cipher.parameters
        val iv = params.getParameterSpec(GCMParameterSpec::class.java).iv
        saveIv(iv)

        return encodedBytes


    }

    private fun decrypt(cipherText: ByteArray): ByteArray {
        val iv = getIv()
        val ivSpec = GCMParameterSpec(128, iv)
        val keyStoreKey = keyStore.getKey(KEY_ALIAS, null) as SecretKey
        val cipher = Cipher.getInstance(AES_MODE)
        cipher.init(Cipher.DECRYPT_MODE, keyStoreKey, ivSpec)

        return cipher.doFinal(cipherText)
    }

    fun setToken(accessToken: String) {

        val stage1 = accessToken.replace("-", "+1")     //
        val stage2 = stage1.replace("_", "+2")          //Replace symbols that doesn`t supported by Base64 format
        val stage3 = stage2.replace("%", "+3")          //
        Timber.e("$stage3 SET TOKEN")
        val tokenBytes: ByteArray = encrypt(Base64.decode(stage3, Base64.NO_WRAP or Base64.NO_PADDING))
        val tokenEncrypted: String = Base64.encodeToString(tokenBytes, Base64.DEFAULT or Base64.NO_PADDING)
        context.getSharedPreferences(Constants.TOKEN, MODE_PRIVATE).edit().putString(Constants.TOKEN, tokenEncrypted).apply()
    }

    fun getToken(): String? {
        val tokenEncrypted: String? = context.getSharedPreferences(Constants.TOKEN, MODE_PRIVATE).getString(Constants.TOKEN, null)
        if (tokenEncrypted != null) {
            val tokenDecryptedBytes: ByteArray = decrypt(Base64.decode(tokenEncrypted, Base64.NO_WRAP))
            val tokenDecrypted: String = Base64.encodeToString(tokenDecryptedBytes, Base64.NO_WRAP)

            val stage1 = tokenDecrypted.replace("+1", "-")
            val stage2 = stage1.replace("+2", "_")          //Replace symbols that doesn`t supported by Base64 format
            val stage3 = stage2.replace("+3", "%")
            Timber.e("$stage3 GET TOKEN")

            return stage3
        } else {
            return null
        }
    }

    private fun saveIv(iv: ByteArray) {
        val ivString: String = Base64.encodeToString(iv, flags)
        context.getSharedPreferences("IV", MODE_PRIVATE).edit().putString("IV", ivString).apply()
    }

    private fun getIv(): ByteArray {
        val ivString: String = context.getSharedPreferences("IV", MODE_PRIVATE).getString("IV", null)
        return Base64.decode(ivString, flags)
    }


}