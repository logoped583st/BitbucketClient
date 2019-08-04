package bushuk.stanislau.bitbucketproject.di.modules.global

import bushuk.stanislau.bitbucketproject.di.CiceroneFactory
import bushuk.stanislau.bitbucketproject.di.CiceroneFactoryImpl
import bushuk.stanislau.bitbucketproject.di.CiceroneKey
import bushuk.stanislau.bitbucketproject.di.Cicerones
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
abstract class CiceroneModule {

    @Binds
    abstract fun provideFactory(cicerone: CiceroneFactoryImpl): CiceroneFactory
}

@Module
class CiceroneGlobalModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    @IntoMap
    @CiceroneKey(Cicerones.GLOBAL)
    fun provideGlobalRouter(): Cicerone<Router> = cicerone
}

@Module
class CiceroneDrawerModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    @IntoMap
    @CiceroneKey(Cicerones.DRAWER)
    fun provideDrawerRouter(): Cicerone<Router> = cicerone
}