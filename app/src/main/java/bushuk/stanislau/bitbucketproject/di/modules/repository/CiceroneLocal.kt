package bushuk.stanislau.bitbucketproject.di.modules.repository

import bushuk.stanislau.bitbucketproject.di.scopes.RepositoryScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class CiceroneLocal {

    private val ciceroneLocal: Cicerone<Router> = Cicerone.create()

    @Provides
    @RepositoryScope
    fun provideRoute(): Router = ciceroneLocal.router

    @Provides
    @RepositoryScope
    fun provideHolder(): NavigatorHolder = ciceroneLocal.navigatorHolder

}