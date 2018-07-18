package bushuk.stanislau.bitbucketproject.api

import bushuk.stanislau.bitbucketproject.pojo.User
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface Api {

    @GET("user")
    fun myUserAccessToken(): Single<User>

    @GET("user")
    fun myUserBaseAuth(): Single<User>

    @GET("user")
    fun authSuccessful():Completable
}