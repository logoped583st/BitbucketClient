package bushuk.stanislau.bitbucketproject.di.modules

import android.content.Context
import bushuk.stanislau.bitbucketproject.TokenPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TokenPreferecesModule {

    @Provides
    @Singleton
    fun getTokenPreferences(context: Context)=TokenPreferences(context)
}