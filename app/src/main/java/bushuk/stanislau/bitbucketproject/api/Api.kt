package bushuk.stanislau.bitbucketproject.api

import bushuk.stanislau.bitbucketproject.room.code.BranchesResponse
import bushuk.stanislau.bitbucketproject.room.code.CodeResponse
import bushuk.stanislau.bitbucketproject.room.comments.CommentResponse
import bushuk.stanislau.bitbucketproject.room.commits.CommitResponse
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequestResponse
import bushuk.stanislau.bitbucketproject.room.repositories.CreateRepository
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet
import bushuk.stanislau.bitbucketproject.room.snippets.SnippetsResponse
import bushuk.stanislau.bitbucketproject.room.team.TeamResponse
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.room.user.UserResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface Api {

    @GET("user")
    fun myUser(): Single<User>

    @GET
    fun getBranchWithUrl(@Url url: String): Single<BranchesResponse>

    @GET("repositories/{userName}")
    fun getRepos(@Path("userName") userName: String?, @Query("q", encoded = false) query: String?): Single<RepositoriesResponse>

    @GET("repositories/{userName}/{repoName}/src")
    fun getRepoWithName(@Path("userName") userName: String, @Path("repoName") repoName: String): Single<CodeResponse>

    @GET("repositories/{userName}/{repoName}/src/{path}")
    fun getRepoWithNamePath(@Path("userName") userName: String, @Path("repoName", encoded = false) repoName: String,
                            @Path("path", encoded = false) path: String): Single<CodeResponse>

    @GET("repositories/{userName}/{repoName}/watchers")
    fun getWatchersRepo(@Path("userName") userName: String, @Path("repoName", encoded = false) repoName: String): Single<Followers>

    @GET//request for getting info from next page, Url we take from previous request
    fun getRepoWithNameNextPage(@Url url: String): Single<CodeResponse>

    @GET//request for getting info from next page, Url we take from previous request
    fun getReposNextPage(@Url url: String): Single<RepositoriesResponse>

    @GET("users/{userName}/followers")
    fun getFollowers(@Path("userName") user: String): Single<UserResponse>

    @GET//request for getting info from next page, Url we take from previous request
    fun getFollowersNextPage(@Url url: String): Single<UserResponse>

    @GET("repositories/{userName}/{repoName}/pullrequests")
    fun getPullRequests(@Path("userName") userName: String, @Path("repoName", encoded = false) repoName: String,
                        @Query("q", encoded = false) query: String?,
                        @Query("sort", encoded = false) sort: String?): Single<PullRequestResponse>

    @GET
    fun getPullRequestNextPage(@Url url: String): Single<PullRequestResponse>

    @GET("users/{userName}/following")
    fun getFollowing(@Path("userName") userName: String): Single<Followers>

    @GET("snippets/{userName}")
    fun getSnippets(@Path("userName") userName: String): Single<SnippetsResponse>

    @GET//request for getting info from next page, Url we take from previous request
    fun getSnippetsNextPage(@Url url: String): Single<SnippetsResponse>

    @GET
    fun getCommitWithUrl(@Url url: String): Single<CommitResponse>

    @GET("repositories/{userName}/{repoName}/pullrequests/{id}")
    fun getPullRequest(@Path("userName") userName: String, @Path("repoName") repoName: String,
                       @Path("id") id: String): Single<PullRequest>

    @GET
    fun getPullRequestComments(@Url url: String): Single<CommentResponse>

    @POST
    fun mergePullRequest(@Url url: String): Single<PullRequest>

    @POST
    fun approvePullRequest(@Url url: String): Completable

    @DELETE
    fun unApprovePullRequest(@Url url: String): Completable

    @GET
    fun getSnippet(@Url url: String): Single<Snippet>

    @POST("repositories/{userName}/{repoName}")
    fun createRepository(@Path("userName") userName: String, @Path("repoName") repoName: String,
                         @Body createRepository: CreateRepository): Completable

    @GET("teams")
    fun getTeams(@Query("role") role: String): Single<TeamResponse>

}
