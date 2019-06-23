package bushuk.stanislau.bitbucketproject.di.modules

import bushuk.stanislau.bitbucketproject.di.modules.global.CiceroneModule
import bushuk.stanislau.bitbucketproject.di.modules.global.CryptoModule
import bushuk.stanislau.bitbucketproject.di.modules.global.PreferencesModule
import bushuk.stanislau.bitbucketproject.di.modules.global.RetrofitModule
import dagger.Module

@Module(includes = [CiceroneModule::class, PreferencesModule::class, CryptoModule::class, RetrofitModule::class])
abstract class GlobalModule