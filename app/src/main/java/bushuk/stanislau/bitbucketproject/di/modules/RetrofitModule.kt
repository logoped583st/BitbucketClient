package bushuk.stanislau.bitbucketproject.di.modules

import android.util.Log
import bushuk.stanislau.bitbucketproject.api.Api
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideOkhttp(interceptorLogs: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptorLogs)
            .build()

    @Provides
    @Singleton
    fun getApi(client: OkHttpClient): Api {
        return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.bitbucket.org/2.0/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Api::class.java) as Api
    }
}
