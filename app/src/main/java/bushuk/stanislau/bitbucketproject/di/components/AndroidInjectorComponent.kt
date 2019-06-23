package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.di.modules.AppModule
import bushuk.stanislau.bitbucketproject.di.modules.GlobalModule
import bushuk.stanislau.bitbucketproject.di.modules.ViewModelModule
import bushuk.stanislau.bitbucketproject.di.modules.auth.AuthLoginModule
import bushuk.stanislau.bitbucketproject.di.modules.global.CiceroneModule
import bushuk.stanislau.bitbucketproject.di.modules.global.CryptoModule
import bushuk.stanislau.bitbucketproject.di.modules.global.PreferencesModule
import bushuk.stanislau.bitbucketproject.di.modules.global.UserModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [ViewModelModule::class,
    GlobalModule::class,
    UserModule::class,
    AuthLoginModule::class,
    AppModule::class])
interface AndroidInjectorComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {
        abstract fun preferenceModule(module: PreferencesModule): Builder
        abstract fun cryptoModule(module: CryptoModule): Builder
    }
}