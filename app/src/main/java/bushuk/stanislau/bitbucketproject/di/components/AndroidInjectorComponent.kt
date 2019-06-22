package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.di.modules.AppModule
import bushuk.stanislau.bitbucketproject.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [ViewModelModule::class,
    AppModule::class])
interface AndroidInjectorComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}