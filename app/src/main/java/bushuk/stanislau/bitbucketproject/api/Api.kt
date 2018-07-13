package bushuk.stanislau.bitbucketproject.api

import bushuk.stanislau.bitbucketproject.pojo.User
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("user")
    fun myUser(@Query("access_token", encoded = true) token: String): Single<User>
}