package bushuk.stanislau.bitbucketproject.api

import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.snippets.SnippetsResponce
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import retrofit2.http.*

interface Api {


    @GET("user")
    fun myUser(): Single<User>

    @GET("users/{userName}")
    fun getUser(@Path("userName")userName: String):Single<User>

    @GET("repositories/{userName}")
    fun getRepos(@Path("userName") userName: String, @QueryMap(encoded=true) query: Map<String, String>? ): Single<RepositoriesResponse>

    @GET//request for getting info from next page, Url we take from previous request
    fun getReposNextPage(@Url url: String): Single<RepositoriesResponse>

    @GET("users/{userName}/followers")
    fun getFollowers(@Path("userName") user: String): Single<Followers>

    @GET//request for getting info from next page, Url we take from previous request
    fun getFollowersNextPage(@Url url: String): Single<Followers>

    @GET("users/{userName}/following")
    fun getFollowing(@Path("userName") userName: String): Single<Followers>

    @GET//request for getting info from next page, Url we take from previous request
    fun getFollowingNextPage(@Url url: String): Single<Followers>

    @GET("snippets/{userName}")
    fun getSnippets(@Path("userName") userName: String): Single<SnippetsResponce>

    @GET//request for getting info from next page, Url we take from previous request
    fun getSnippetsNextPage(@Url url: String): Single<SnippetsResponce>

}