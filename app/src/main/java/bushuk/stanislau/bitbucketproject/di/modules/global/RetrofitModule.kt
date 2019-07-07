package bushuk.stanislau.bitbucketproject.di.modules.global

import android.util.Log
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.api.ScalarApi
import bushuk.stanislau.bitbucketproject.utils.preferences.ISharedPreferencesUtil
import bushuk.stanislau.bitbucketproject.utils.retrofit.AuthorizationInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    fun provideInterceptorLogs(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { msg ->
            Log.i("okhttp", msg)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder()
            .setLenient()
            .create()

    @Provides
    fun provideOkhttp(httpLoggingInterceptor: HttpLoggingInterceptor, authorizationInterceptor: AuthorizationInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(authorizationInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    @Provides
    fun provideAuthorizationInterceptor(sharedPreferences: ISharedPreferencesUtil) = AuthorizationInterceptor(sharedPreferences)


    @Provides
    @Singleton
    fun getApi(gson: Gson, okHttpClient: OkHttpClient): Api {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.bitbucket.org/2.0/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(Api::class.java) as Api
    }

    @Provides
    @Singleton
    fun getPlainApi(okHttpClient: OkHttpClient):ScalarApi{
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.bitbucket.org/2.0/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build().create(ScalarApi::class.java) as ScalarApi
    }

}
