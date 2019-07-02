package bushuk.stanislau.bitbucketproject.utils.crypt

import android.util.Base64

import java.nio.charset.Charset

import javax.inject.Inject

class BaseCrypto @Inject constructor(val storageCipher: CryptApi23) {

    private val charset = Charset.forName("UTF-8")

    @Throws(Exception::class)
    fun encrypt(value: String): String {
        return Base64.encodeToString(storageCipher.encrypt(value.toByteArray(charset)),0)
    }

    @Throws(Exception::class)
    fun decrypt(text: String): String {
        return decodeRawValue(text)
    }

    @Throws(Exception::class)
    private fun decodeRawValue(value: String): String {
        val data = Base64.decode(value, 0)
        val result = storageCipher.decrypt(data)

        return String(result, charset)
    }
}