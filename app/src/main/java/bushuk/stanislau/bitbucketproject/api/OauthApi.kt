package bushuk.stanislau.bitbucketproject.api

import bushuk.stanislau.bitbucketproject.room.OauthResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OauthApi {

    @FormUrlEncoded
    @POST("oauth2/access_token")
    fun getOauth(
            @Field("grant_type") grantType: String,
            @Field("code") code: String,
            @Field("client_id") clientId: String,
            @Field("client_secret") clientSecret: String
    ): Single<OauthResponse>


    @FormUrlEncoded
    @POST("oauth2/access_token")
    fun refreshToken(
            @Field("grant_type") grantType: String,
            @Field("refresh_token") refreshToken: String,
            @Field("client_id") clientId: String,
            @Field("client_secret") clientSecret: String
    ): Single<OauthResponse>
}