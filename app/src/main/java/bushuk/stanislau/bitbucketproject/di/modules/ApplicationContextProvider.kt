package bushuk.stanislau.bitbucketproject.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationContextProvider(val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

}