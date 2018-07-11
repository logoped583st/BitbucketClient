package bushuk.stanislau.bitbucketproject.di.modules

import android.content.Context
import bushuk.stanislau.bitbucketproject.TokenPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TokenPreferencesModule(private val context: Context) {

    @Provides
    @Singleton
    fun getTokenPreferences()=TokenPreferences(context)
}