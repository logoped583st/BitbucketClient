package bushuk.stanislau.bitbucketproject.utils.TokenUtils

import android.content.Context
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
        val tokenBytes: ByteArray = encrypt(Base64.decode(StringTokenUtils.convertToBase64(accessToken), Base64.NO_WRAP or Base64.NO_PADDING))
        val tokenEncrypted: String = Base64.encodeToString(tokenBytes, Base64.DEFAULT or Base64.NO_PADDING)
        context.getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE).edit().putString(Constants.TOKEN, tokenEncrypted).apply()
    }

    override fun getToken(): String? {
        val tokenEncrypted: String? = context.getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE).getString(Constants.TOKEN, null)
        Timber.e("GET TOKEN API 19")
        if (tokenEncrypted != null) {
            val tokenDecryptedBytes: ByteArray = decrypt(Base64.decode(tokenEncrypted, Base64.NO_WRAP))
            val tokenDecrypted: String = Base64.encodeToString(tokenDecryptedBytes, Base64.NO_WRAP)

            return StringTokenUtils.convertToNormal(tokenDecrypted)
        } else {
            return null
        }
    }

}
