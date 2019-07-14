package bushuk.stanislau.bitbucketproject.di.modules.global

import bushuk.stanislau.bitbucketproject.di.CiceroneKey
import bushuk.stanislau.bitbucketproject.di.Cicerones
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class CiceroneDrawerModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    @IntoMap
    @CiceroneKey(Cicerones.DRAWER)
    fun provideDrawerRouter(): Cicerone<Router> = cicerone
}