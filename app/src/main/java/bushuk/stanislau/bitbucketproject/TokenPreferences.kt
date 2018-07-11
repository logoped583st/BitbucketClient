package bushuk.stanislau.bitbucketproject

import android.content.Context

class TokenPreferences(private val context: Context) {

    fun setToken(token: String) {
        context.getSharedPreferences(Constants.TOKEN_PREFERENCES, Context.MODE_PRIVATE).edit()
                .putString(Constants.TOKEN, token).apply()
    }

    fun getToken(): String? = context.getSharedPreferences(Constants.TOKEN_PREFERENCES, Context.MODE_PRIVATE)
            .getString(Constants.TOKEN, null)
}