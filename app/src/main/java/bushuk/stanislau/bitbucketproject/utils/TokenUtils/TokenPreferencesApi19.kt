package bushuk.stanislau.bitbucketproject.utils.TokenUtils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.util.Base64
import bushuk.stanislau.bitbucketproject.Constants
import timber.log.Timber
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*
import javax.crypto.Cipher
import javax.inject.Inject
import javax.security.auth.x500.X500Principal


@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class TokenPreferencesApi19 @Inject constructor(val context: Context) : TokenPreferences {

    private val AndroidKeyStore = Constants.KEY_STRORE
    private val KEY_ALIAS = Constants.TOKEN
    private val RSA_MODE = "RSA/ECB/PKCS1Padding"
    private val PROVIDER = "AndroidOpenSSL"


    private lateinit var keyStore: KeyStore

    init {
        init()
    }

    private fun init() {
        keyStore = KeyStore.getInstance(AndroidKeyStore)
        keyStore.load(null)
        Timber.e("INIT TOKEN API 19")

        // Generate the RSA key pairs
        if (!keyStore.containsAlias(KEY_ALIAS)) {
            // Generate a key pair for encryption
            val start = Calendar.getInstance()
            val end = Calendar.getInstance()
            end.add(Calendar.YEAR, 30)

            val spec = KeyPairGeneratorSpec.Builder(context)
                    .setAlias(KEY_ALIAS)
                    .setSubject(X500Principal("CN=$KEY_ALIAS"))
                    .setSerialNumber(BigInteger.TEN)
                    .setStartDate(start.time)
                    .setEndDate(end.time)
                    .build()
            val kpg = KeyPairGenerator.getInstance("RSA", AndroidKeyStore)
            kpg.initialize(spec)
            kpg.generateKeyPair()
        }
    }


    private fun encrypt(initialText: ByteArray): ByteArray {
        Timber.i("encrypt")
        val privateKeyEntry = keyStore.getEntry(KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
        val publicKey = privateKeyEntry.certificate.publicKey as RSAPublicKey

        val cipher = Cipher.getInstance(RSA_MODE, PROVIDER)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)

        return cipher.doFinal(initialText)
    }


    private fun decrypt(cipherText: ByteArray): ByteArray {

        val privateKeyEntry = keyStore.getEntry(KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
        val privateKey = privateKeyEntry.privateKey as RSAPrivateKey

        val cipher = Cipher.getInstance(RSA_MODE, PROVIDER)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)

        return cipher.doFinal(cipherText)
    }

    override fun setToken(accessToken: String) {

        val stage1 = accessToken.replace("-", "+1")     //
        val stage2 = stage1.replace("_", "+2")          //Replace symbols that doesn`t supported by Base64 format
        val stage3 = stage2.replace("%", "+3")          //
        Timber.e("$stage3 SET TOKEN")
        Timber.e("SET TOKEN API 19")
        val tokenBytes: ByteArray = encrypt(Base64.decode(stage3, Base64.NO_WRAP or Base64.NO_PADDING))
        val tokenEncrypted: String = Base64.encodeToString(tokenBytes, Base64.DEFAULT or Base64.NO_PADDING)
        context.getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE).edit().putString(Constants.TOKEN, tokenEncrypted).apply()
    }

    override fun getToken(): String? {
        val tokenEncrypted: String? = context.getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE).getString(Constants.TOKEN, null)
        Timber.e("GET TOKEN API 19")
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

}
