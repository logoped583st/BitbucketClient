package bushuk.stanislau.bitbucketproject.utils.preferences

import android.content.Context
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.utils.crypt.Crypto
import javax.inject.Inject


class SharedPreferencesUtil @Inject constructor(val context: Context) {

    @Inject
    lateinit var crypto: Crypto

    init {
        App.component.inject(this)
    }

    fun setToken(accessToken: String) {
        val tokenBytes: ByteArray = crypto.encrypt(accessToken.toByteArray(Charsets.ISO_8859_1))
        val tokenEncrypted: String = String(tokenBytes, Charsets.ISO_8859_1)
        context.getSharedPreferences(Constants.TOKEN_PREFERENCES, Context.MODE_PRIVATE).edit()
                .putString(Constants.TOKEN_PREFERENCES, tokenEncrypted).apply()
    }

    fun getToken(): String? {
        val tokenEncrypted: String? = context.getSharedPreferences(Constants.TOKEN_PREFERENCES, Context.MODE_PRIVATE)
                .getString(Constants.TOKEN_PREFERENCES, null)

        if (tokenEncrypted != null) {
            val tokenDecryptedBytes: ByteArray = tokenEncrypted.toByteArray(Charsets.ISO_8859_1)
            val tokenDecrypted: String = String(crypto.decrypt(tokenDecryptedBytes), Charsets.ISO_8859_1)

            return tokenDecrypted
        } else {
            return null
        }
    }

    fun clearToken(){
        context.getSharedPreferences(Constants.TOKEN_PREFERENCES,Context.MODE_PRIVATE).edit()
                .putString(Constants.TOKEN_PREFERENCES,null).apply()
    }

}
