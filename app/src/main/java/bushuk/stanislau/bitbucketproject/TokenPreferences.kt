package bushuk.stanislau.bitbucketproject

import android.content.Context
import java.security.KeyStore

class TokenPreferences {

    private val keyStore: KeyStore = KeyStore.getInstance(Constants.KEY_STRORE)

    fun setToken(token: String) {
        keyStore.load(null)
        keyStore.provider.put(Constants.TOKEN, token)

    }

    fun getToken(): String? = keyStore.provider.get(Constants.TOKEN) as String?
}