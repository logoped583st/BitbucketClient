package bushuk.stanislau.bitbucketproject.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class CiceroneModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideRoute():Router = cicerone.router

    @Provides
    @Singleton
    fun provideHolder():NavigatorHolder = cicerone.navigatorHolder

}