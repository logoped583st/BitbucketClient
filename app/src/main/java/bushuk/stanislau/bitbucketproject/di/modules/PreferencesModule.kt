package bushuk.stanislau.bitbucketproject.di.modules

import android.content.Context
import bushuk.stanislau.bitbucketproject.utils.sharedPreferencesUtils.SharedPreferencesUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule(val context: Context) {

    @Provides
    @Singleton
    fun tokenPreferencesModule(): SharedPreferencesUtil = SharedPreferencesUtil(context)
}