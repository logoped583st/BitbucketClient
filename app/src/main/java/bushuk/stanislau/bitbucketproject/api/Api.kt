package bushuk.stanislau.bitbucketproject.api

import bushuk.stanislau.bitbucketproject.room.code.BranchesResponse
import bushuk.stanislau.bitbucketproject.room.code.CodeResponse
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequestResponse
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.snippets.SnippetsResponce
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {


    @GET("user")
    fun myUser(): Single<User>

    @GET
    fun getBranchWithUrl(@Url url: String): Single<BranchesResponse>

    @GET("repositories/{userName}")
    fun getRepos(@Path("userName") userName: String, @Query("q", encoded = false) query: String?): Single<RepositoriesResponse>

    @GET("repositories/{userName}/{repoName}/src")
    fun getRepoWithName(@Path("userName") userName: String, @Path("repoName") repoName: String): Single<CodeResponse>


    @GET("repositories/{userName}/{repoName}/src/{path}")
    fun getRepoWithNamePath(@Path("userName") userName: String, @Path("repoName")
    repoName: String, @Path("path", encoded = false) path: String): Single<CodeResponse>

    @GET//request for getting info from next page, Url we take from previous request
    fun getRepoWithNameNextPage(@Url url: String): Single<CodeResponse>

    @GET//request for getting info from next page, Url we take from previous request
    fun getReposNextPage(@Url url: String): Single<RepositoriesResponse>

    @GET("users/{userName}/followers")
    fun getFollowers(@Path("userName") user: String): Single<Followers>

    @GET//request for getting info from next page, Url we take from previous request
    fun getFollowersNextPage(@Url url: String): Single<Followers>

    @GET("repositories/{userName}/{repoName}/pullrequests")
    fun getPullRequests(@Path("userName") userName: String, @Path("repoName", encoded = false
    ) repoName: String,
                        @Query("q", encoded = false) query: String?): Single<PullRequestResponse>

    @GET
    fun getPullRequestNextPage(@Url url:String):Single<PullRequestResponse>

    @GET("users/{userName}/following")
    fun getFollowing(@Path("userName") userName: String): Single<Followers>

    @GET//request for getting info from next page, Url we take from previous request
    fun getFollowingNextPage(@Url url: String): Single<Followers>

    @GET("snippets/{userName}")
    fun getSnippets(@Path("userName") userName: String): Single<SnippetsResponce>

    @GET//request for getting info from next page, Url we take from previous request
    fun getSnippetsNextPage(@Url url: String): Single<SnippetsResponce>

}