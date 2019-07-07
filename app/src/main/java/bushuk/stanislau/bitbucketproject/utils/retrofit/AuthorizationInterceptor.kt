package bushuk.stanislau.bitbucketproject.utils.retrofit

import bushuk.stanislau.bitbucketproject.utils.preferences.ISharedPreferencesUtil
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthorizationInterceptor @Inject constructor(val sharedPreferences: ISharedPreferencesUtil) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val original = chain!!.request()
        val builder = original.newBuilder()

        val auth = sharedPreferences.getToken()
        val originalHttpUrl = original.url()
        val url = originalHttpUrl.newBuilder()

        if (auth != null) {
            if (auth.contains("Basic")) {
                builder.header("Authorization", auth)
            } else {
                url.addEncodedQueryParameter("access_token", auth)
            }
        }

        val request = builder
                .url(url.build())
                .build()

        return chain.proceed(request)
    }
}