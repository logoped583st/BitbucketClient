package bushuk.stanislau.bitbucketproject.utils.crypt

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.security.keystore.StrongBoxUnavailableException
import bushuk.stanislau.bitbucketproject.constants.Constants
import java.math.BigInteger
import java.security.*
import java.security.spec.AlgorithmParameterSpec
import java.util.*
import javax.crypto.Cipher
import javax.inject.Inject
import javax.security.auth.x500.X500Principal


class CryptApi19 @Inject constructor(val context: Context) : Crypto {
    private val KEYSTORE_PROVIDER_ANDROID = Constants.KEY_STORE
    private val KEY_ALIAS = Constants.TOKEN_PREFERENCES
    private val TYPE_RSA = "RSA"

    init {
        RSACipher18Implementation(context)
    }

    @Throws(Exception::class)
    fun RSACipher18Implementation(context: Context) {
        createRSAKeysIfNeeded(context)
    }

    @Throws(Exception::class)
    fun wrap(key: Key): ByteArray {
        val publicKey = getPublicKey()
        val cipher = getRSACipher()
        cipher.init(Cipher.WRAP_MODE, publicKey)

        return cipher.wrap(key)
    }

    @Throws(Exception::class)
    fun unwrap(wrappedKey: ByteArray, algorithm: String): Key {
        val privateKey = getPrivateKey()
        val cipher = getRSACipher()
        cipher.init(Cipher.UNWRAP_MODE, privateKey)

        return cipher.unwrap(wrappedKey, algorithm, Cipher.SECRET_KEY)
    }

    @Throws(Exception::class)
    override fun encrypt(input: ByteArray): ByteArray {
        val publicKey = getPublicKey()
        val cipher = getRSACipher()
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)

        return cipher.doFinal(input)
    }

    @Throws(Exception::class)
    override fun decrypt(input: ByteArray): ByteArray {
        val privateKey = getPrivateKey()
        val cipher = getRSACipher()
        cipher.init(Cipher.DECRYPT_MODE, privateKey)

        return cipher.doFinal(input)
    }

    @Throws(Exception::class)
    private fun getPrivateKey(): PrivateKey {
        val ks = KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID)
        ks.load(null)

        val key = (ks.getKey(KEY_ALIAS, null)
                ?: throw Exception("No key found under alias: $KEY_ALIAS")) as? PrivateKey
                ?: throw Exception("Not an instance of a PrivateKey")

        return key
    }

    @Throws(Exception::class)
    private fun getPublicKey(): PublicKey {
        val ks = KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID)
        ks.load(null)

        val cert = ks.getCertificate(KEY_ALIAS)
                ?: throw Exception("No certificate found under alias: $KEY_ALIAS")

        return cert.publicKey ?: throw Exception("No key found under alias: $KEY_ALIAS")
    }

    @Throws(Exception::class)
    private fun getRSACipher(): Cipher {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidOpenSSL") // error in android 6: InvalidKeyException: Need RSA private or public key
        } else {
            Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidKeyStoreBCWorkaround") // error in android 5: NoSuchProviderException: Provider not available: AndroidKeyStoreBCWorkaround
        }
    }

    @Throws(Exception::class)
    private fun createRSAKeysIfNeeded(context: Context) {
        val ks = KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID)
        ks.load(null)

        val privateKey = ks.getKey(KEY_ALIAS, null)
        if (privateKey == null) {
            createKeys(context)
        }
    }

    @SuppressLint("NewApi")
    @Throws(Exception::class)
    private fun createKeys(context: Context) {
        val start = Calendar.getInstance()
        val end = Calendar.getInstance()
        end.add(Calendar.YEAR, 25)

        val kpGenerator = KeyPairGenerator.getInstance(TYPE_RSA, KEYSTORE_PROVIDER_ANDROID)

        val spec: AlgorithmParameterSpec = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            KeyPairGeneratorSpec.Builder(context)
                    .setAlias(KEY_ALIAS)
                    .setSubject(X500Principal("CN=$KEY_ALIAS"))
                    .setSerialNumber(BigInteger.valueOf(1))
                    .setStartDate(start.time)
                    .setEndDate(end.time)
                    .build()
        } else {
            val builder = KeyGenParameterSpec.Builder(KEY_ALIAS, KeyProperties.PURPOSE_DECRYPT or KeyProperties.PURPOSE_ENCRYPT)
                    .setCertificateSubject(X500Principal("CN=$KEY_ALIAS"))
                    .setDigests(KeyProperties.DIGEST_SHA256)
                    .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                    .setCertificateSerialNumber(BigInteger.valueOf(1))
                    .setCertificateNotBefore(start.time)
                    .setCertificateNotAfter(end.time)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                builder.setIsStrongBoxBacked(true)
            }

            builder.build()
        }
        try {
            kpGenerator.initialize(spec)
            kpGenerator.generateKeyPair()
        } catch (se: StrongBoxUnavailableException) {
            KeyGenParameterSpec.Builder(KEY_ALIAS, KeyProperties.PURPOSE_DECRYPT or KeyProperties.PURPOSE_ENCRYPT)
                    .setCertificateSubject(X500Principal("CN=$KEY_ALIAS"))
                    .setDigests(KeyProperties.DIGEST_SHA256)
                    .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                    .setCertificateSerialNumber(BigInteger.valueOf(1))
                    .setCertificateNotBefore(start.time)
                    .setCertificateNotAfter(end.time)
                    .build()
            kpGenerator.initialize(spec)
            kpGenerator.generateKeyPair()
        }

    }
}