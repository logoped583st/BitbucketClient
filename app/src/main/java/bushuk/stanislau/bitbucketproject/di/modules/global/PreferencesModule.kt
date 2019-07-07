package bushuk.stanislau.bitbucketproject.di.modules.global

import bushuk.stanislau.bitbucketproject.utils.preferences.ISharedPreferencesUtil
import bushuk.stanislau.bitbucketproject.utils.preferences.SharedPreferencesUtil
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class PreferencesModule {

    @Binds
    @Singleton
    abstract fun tokenPreferencesModule(sharedPreferencesUtil: SharedPreferencesUtil): ISharedPreferencesUtil
}