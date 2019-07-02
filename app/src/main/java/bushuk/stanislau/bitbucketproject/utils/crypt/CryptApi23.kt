package bushuk.stanislau.bitbucketproject.utils.crypt

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.Base64
import timber.log.Timber
import java.security.Key
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject


@TargetApi(Build.VERSION_CODES.M)
class CryptApi23 @Inject constructor(val rsaCipher: CryptApi19,
                                     val context: Context) : Crypto {

    private val ivSize = 16
    private val keySize = 16
    private val KEY_ALGORITHM = "AES"
    private val AES_PREFERENCES_KEY = "VGhpcyBpcyB0aGUga2V5IGZvciBhIHNlY3VyZSBzdG9yYWdlIEFFUyBLZXkK"
    private val SHARED_PREFERENCES_NAME = "FlutterSecureKeyStorage"

    lateinit var secretKey: Key
    val cipher: Cipher by lazy { Cipher.getInstance("AES/CBC/PKCS7Padding") }
    lateinit var secureRandom: SecureRandom

    init {
        storageCipher18Implementation()
    }

    @Throws(Exception::class)
    fun storageCipher18Implementation() {
        secureRandom = SecureRandom()

        val preferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()

        val aesKey = preferences.getString(AES_PREFERENCES_KEY, null)

        aesKey.let {
            val encrypted: ByteArray
            try {
                encrypted = Base64.decode(aesKey, Base64.DEFAULT)
                secretKey = rsaCipher.unwrap(encrypted, KEY_ALGORITHM)
                return
            } catch (e: Exception) {
                Timber.e("unwrap key failed  $e")
            }
        }

        val key = ByteArray(keySize)
        secureRandom.nextBytes(key)
        secretKey = SecretKeySpec(key, KEY_ALGORITHM)

        val encryptedKey = rsaCipher.wrap(secretKey)
        editor.putString(AES_PREFERENCES_KEY, Base64.encodeToString(encryptedKey, Base64.DEFAULT))
        editor.apply()
    }

    @Throws(Exception::class)
    override fun encrypt(byteArray: ByteArray): ByteArray {
        val iv = ByteArray(ivSize)
        secureRandom.nextBytes(iv)

        val ivParameterSpec = IvParameterSpec(iv)

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)

        val payload = cipher.doFinal(byteArray)
        val combined = ByteArray(iv.size + payload.size)

        System.arraycopy(iv, 0, combined, 0, iv.size)
        System.arraycopy(payload, 0, combined, iv.size, payload.size)

        return combined
    }

    @Throws(Exception::class)
    override fun decrypt(byteArray: ByteArray): ByteArray {
        val iv = ByteArray(ivSize)
        System.arraycopy(byteArray, 0, iv, 0, iv.size)
        val ivParameterSpec = IvParameterSpec(iv)

        val payloadSize = byteArray.size - ivSize
        val payload = ByteArray(payloadSize)
        System.arraycopy(byteArray, iv.size, payload, 0, payloadSize)

        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)

        return cipher.doFinal(payload)
    }


}