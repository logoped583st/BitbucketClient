package bushuk.stanislau.bitbucketproject.utils.preferences

import android.content.Context
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.utils.crypt.BaseCrypto
import javax.inject.Inject


class SharedPreferencesUtil @Inject constructor(val context: Context, val crypto: BaseCrypto) : ISharedPreferencesUtil {


    override fun setToken(accessToken: String) {
        val tokenEncrypted = crypto.encrypt(accessToken)
        context.getSharedPreferences(Constants.TOKEN_PREFERENCES, Context.MODE_PRIVATE).edit()
                .putString(Constants.TOKEN_PREFERENCES, tokenEncrypted).apply()
    }

    override fun getToken(): String? {
        val tokenEncrypted: String? = context.getSharedPreferences(Constants.TOKEN_PREFERENCES, Context.MODE_PRIVATE)
                .getString(Constants.TOKEN_PREFERENCES, null)

        return if (tokenEncrypted != null) {
            crypto.decrypt(tokenEncrypted)
        } else {
            null
        }
    }

    override fun clearToken(){
        context.getSharedPreferences(Constants.TOKEN_PREFERENCES,Context.MODE_PRIVATE).edit()
                .putString(Constants.TOKEN_PREFERENCES,null).apply()
    }

}

interface ISharedPreferencesUtil {
    fun clearToken()
    fun getToken(): String?
    fun setToken(accessToken: String)
}
