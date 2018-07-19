package bushuk.stanislau.bitbucketproject.api

import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("user")
    fun myUser(): Single<User>


    @GET("user")
    fun authSuccessful(): Completable


}