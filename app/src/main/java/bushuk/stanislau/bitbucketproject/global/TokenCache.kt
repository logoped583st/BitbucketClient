package bushuk.stanislau.bitbucketproject.global

import bushuk.stanislau.bitbucketproject.utils.preferences.ISharedPreferencesUtil
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenCache @Inject constructor(private val sharedPreferences: ISharedPreferencesUtil) : ITokenCache {

    override var accessToken: String? = sharedPreferences.getToken()

    override var refreshToken: String? = sharedPreferences.getRefreshToken()

    override fun onNewAccessToken(accessToken: String) {
        this.accessToken = accessToken
        sharedPreferences.setToken(accessToken)
    }

    override fun onNewRefreshToken(refreshToken: String) {
        this.refreshToken = refreshToken
        sharedPreferences.setRefreshToken(refreshToken)
    }

}

interface ITokenCache {

    val accessToken: String?

    val refreshToken: String?

    fun onNewAccessToken(accessToken: String)

    fun onNewRefreshToken(refreshToken: String)
}