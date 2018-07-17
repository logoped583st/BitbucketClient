package bushuk.stanislau.bitbucketproject.di.modules

import android.content.Context
import bushuk.stanislau.bitbucketproject.utils.TokenUtils.TokenPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TokenPreferencesModule(val context: Context) {

    @Provides
    @Singleton
    fun tokenPreferencesModule(): TokenPreferences = TokenPreferences(context)

}