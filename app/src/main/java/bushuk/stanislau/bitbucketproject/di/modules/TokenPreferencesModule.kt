package bushuk.stanislau.bitbucketproject.di.modules

import android.content.Context
import bushuk.stanislau.bitbucketproject.utils.TokenUtils.TokenPreferences
import bushuk.stanislau.bitbucketproject.utils.TokenUtils.TokenPreferencesApi19
import bushuk.stanislau.bitbucketproject.utils.TokenUtils.TokenPreferencesApi23
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TokenPreferencesModule(val context: Context) {

    @Provides
    @Singleton
    fun getTokenPreferences(): TokenPreferences {
        val version = android.os.Build.VERSION.SDK_INT
        val tokenPreferences: TokenPreferences

        if (version >= 23) {
            tokenPreferences = TokenPreferencesApi23(context)
        } else {
            tokenPreferences = TokenPreferencesApi19(context)
        }

        return tokenPreferences
    }


}