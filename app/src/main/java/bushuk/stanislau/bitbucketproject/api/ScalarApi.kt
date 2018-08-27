package bushuk.stanislau.bitbucketproject.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ScalarApi {

    @GET("repositories/{userName}/{repoName}/src/{path}")
    fun getCodeOfFile(@Path("userName") userName: String, @Path("repoName", encoded = false) repoName: String,
                      @Path("path", encoded = false) path: String): Single<String>
}