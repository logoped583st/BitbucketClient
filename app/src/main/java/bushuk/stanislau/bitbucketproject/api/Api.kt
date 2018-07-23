package bushuk.stanislau.bitbucketproject.api

import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("user")
    fun myUser(): Single<User>

    @GET("repositories/{user}")
    fun getRepos(@Path("user") user: String): Single<RepositoriesResponse>
}