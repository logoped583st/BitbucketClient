package bushuk.stanislau.bitbucketproject.di.modules

import bushuk.stanislau.bitbucketproject.di.modules.global.*
import dagger.Module

@Module(includes = [
    CiceroneModule::class, CiceroneGlobalModule::class,
    CiceroneDrawerModule::class,
    PreferencesModule::class, CryptoModule::class,
    RetrofitModule::class, ApplicationContextProvider::class])
abstract class GlobalModule