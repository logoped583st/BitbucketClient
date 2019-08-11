package bushuk.stanislau.bitbucketproject.api

import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.global.ITokenCache
import bushuk.stanislau.bitbucketproject.room.OauthResponse
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.extensions.mapUnAuthorize
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class BaseApiRepository(
        private val tokenCache: ITokenCache,
        private val oauthApi: OauthApi,
        private val api: Api
) {


    fun authSuccessful(): Single<User> = api.myUser().mapUnAuthorize(tokenCache.refreshToken) { refreshOauthToken() }

    fun getMyUser(): Single<User> {
        return handleUnAuthorize(api.myUser().subscribeOn(Schedulers.io()))
    }

    private fun refreshOauthToken(): Single<OauthResponse> {
        return oauthApi.refreshToken(Constants.GRANT_TYPE_REFRESH_TOKEN, tokenCache.refreshToken
                ?: "", Constants.CLIENT_ID, Constants.SECRET_ID).subscribeOn(Schedulers.io()).doOnSuccess {
            tokenCache.onNewAccessToken("${Constants.TokenTypes.BEARER.type} ${it.accessToken}")
            tokenCache.onNewRefreshToken(it.refreshToken)
        }
    }

    fun getOauthToken(code: String): Single<OauthResponse> {
        return oauthApi.getOauth(Constants.GRANT_TYPE_AUTH_CODE, code, Constants.CLIENT_ID, Constants.SECRET_ID).subscribeOn(Schedulers.io())
    }

    protected fun <T> handleUnAuthorize(single: Single<T>): Single<T> {
        return single.mapUnAuthorize(tokenCache.refreshToken) { refreshOauthToken() }
    }

}




