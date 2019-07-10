package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.di.modules.AppModule
import bushuk.stanislau.bitbucketproject.di.modules.GlobalModule
import bushuk.stanislau.bitbucketproject.di.modules.ViewModelModule
import bushuk.stanislau.bitbucketproject.di.modules.global.ApplicationContextProvider
import bushuk.stanislau.bitbucketproject.di.modules.global.UserModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [
    ViewModelModule::class,
    GlobalModule::class,
    UserModule::class,
    AppModule::class])
interface AndroidInjectorComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {
        abstract fun appContext(module: ApplicationContextProvider):Builder
    }
}