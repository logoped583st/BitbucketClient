package bushuk.stanislau.bitbucketproject.utils.TokenUtils

import android.content.Context
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.Constants
import bushuk.stanislau.bitbucketproject.utils.CryptUtils.Crypto
import javax.inject.Inject


class TokenPreferences @Inject constructor(val context: Context) {

    @Inject
    lateinit var crypto: Crypto

    init {
        App.component.inject(this)
    }

    fun setToken(accessToken: String) {

        val tokenBytes: ByteArray = crypto.encrypt(accessToken.toByteArray(Charsets.ISO_8859_1))
        val tokenEncrypted: String = String(tokenBytes, Charsets.ISO_8859_1)
        context.getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE).edit().putString(Constants.TOKEN, tokenEncrypted).apply()
    }

    fun getToken(): String? {
        val tokenEncrypted: String? = context.getSharedPreferences(Constants.TOKEN, Context.MODE_PRIVATE).getString(Constants.TOKEN, null)

        if (tokenEncrypted != null) {
            val tokenDecryptedBytes: ByteArray = tokenEncrypted.toByteArray(Charsets.ISO_8859_1)
            val tokenDecrypted: String = String(crypto.decrypt(tokenDecryptedBytes), Charsets.ISO_8859_1)

            return tokenDecrypted
        } else {
            return null
        }
    }

}
