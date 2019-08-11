package bushuk.stanislau.bitbucketproject.utils.retrofit

import bushuk.stanislau.bitbucketproject.constants.Constants.TOKEN_HEADER
import bushuk.stanislau.bitbucketproject.global.ITokenCache
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthorizationInterceptor @Inject constructor(private val tokenCache: ITokenCache) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val original = chain!!.request()
        val builder = original.newBuilder()

        val auth = tokenCache.accessToken
        val originalHttpUrl = original.url()
        val url = originalHttpUrl.newBuilder()

        if (auth != null) {
            builder.header(TOKEN_HEADER, auth)
        }

        val request = builder
                .url(url.build())
                .build()

        return chain.proceed(request)
    }
}