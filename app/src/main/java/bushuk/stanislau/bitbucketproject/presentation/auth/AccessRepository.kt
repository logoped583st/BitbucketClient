package bushuk.stanislau.bitbucketproject.presentation.auth

import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.api.BaseApiRepository
import bushuk.stanislau.bitbucketproject.api.OauthApi
import bushuk.stanislau.bitbucketproject.global.ITokenCache
import bushuk.stanislau.bitbucketproject.room.OauthResponse
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import javax.inject.Inject


class AccessRepository @Inject constructor(private val api: Api,
                                           oauthApi: OauthApi,
                                           tokenCache: ITokenCache)
    : BaseApiRepository(tokenCache, oauthApi, api), IAccessRepository

interface IAccessRepository {
    fun authSuccessful(): Single<User>

    fun getOauthToken(code: String): Single<OauthResponse>

    fun getMyUser(): Single<User>

}
