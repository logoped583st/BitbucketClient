package bushuk.stanislau.bitbucketproject.utils.crypt

import android.content.Context
import android.security.KeyPairGeneratorSpec
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.Constants
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*
import javax.crypto.Cipher
import javax.inject.Inject
import javax.security.auth.x500.X500Principal

class CryptApi19 @Inject constructor(val context: Context) : Crypto {
    private val AndroidKeyStore = Constants.KEY_STORE
    private val KEY_ALIAS = Constants.TOKEN_PREFERENCES
    private val RSA_MODE = "RSA/ECB/PKCS1Padding"
    private val PROVIDER = "AndroidOpenSSL"


    private lateinit var keyStore: KeyStore

    init {
        init()
        App.component.inject(this)
    }

    private fun init() {
        keyStore = KeyStore.getInstance(AndroidKeyStore)
        keyStore.load(null)

        if (!keyStore.containsAlias(KEY_ALIAS)) {
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


    override fun encrypt(byteArray: ByteArray): ByteArray {
        val privateKeyEntry = keyStore.getEntry(KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
        val publicKey = privateKeyEntry.certificate.publicKey as RSAPublicKey
        val cipher = Cipher.getInstance(RSA_MODE, PROVIDER)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)

        return cipher.doFinal(byteArray)
    }


    override fun decrypt(byteArray: ByteArray): ByteArray {
        val privateKeyEntry = keyStore.getEntry(KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
        val privateKey = privateKeyEntry.privateKey as RSAPrivateKey
        val cipher = Cipher.getInstance(RSA_MODE, PROVIDER)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)

        return cipher.doFinal(byteArray)
    }
}