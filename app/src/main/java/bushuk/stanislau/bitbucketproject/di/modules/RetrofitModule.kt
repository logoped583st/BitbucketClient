package bushuk.stanislau.bitbucketproject.di.modules

import android.util.Log
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.utils.retrofitUtils.AuthorizationInterceptor
import com.bumptech.glide.RequestBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Module
class RetrofitModule {

    private fun provideInterceptorLogs(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { msg ->
            Log.i("okhttp", msg)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }


    private fun provideOkhttp(): OkHttpClient {


        return OkHttpClient.Builder()
                .addInterceptor(AuthorizationInterceptor())
                .addInterceptor(provideInterceptorLogs())
                .build()
    }



    @Provides
    @Singleton
    fun getApi(): Api {
        Timber.e("SUKA")
        return Retrofit.Builder()
                .client(provideOkhttp())
                .baseUrl("https://api.bitbucket.org/2.0/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Api::class.java) as Api
    }
}
