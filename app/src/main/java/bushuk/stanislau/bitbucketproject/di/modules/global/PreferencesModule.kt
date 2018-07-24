package bushuk.stanislau.bitbucketproject.di.modules.global

import android.content.Context
import bushuk.stanislau.bitbucketproject.utils.preferences.SharedPreferencesUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule(val context: Context) {

    @Provides
    @Singleton
    fun tokenPreferencesModule(): SharedPreferencesUtil = SharedPreferencesUtil(context)
}