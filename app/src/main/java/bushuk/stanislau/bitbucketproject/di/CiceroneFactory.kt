package bushuk.stanislau.bitbucketproject.di

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Singleton


interface CiceroneFactory {
    fun provideCicerone(scope: Cicerones): Cicerone<Router>
}

@Singleton
class CiceroneFactoryImpl @Inject constructor(private val creator: Map<Cicerones, @JvmSuppressWildcards Cicerone<Router>>) : CiceroneFactory {

    override fun provideCicerone(scope: Cicerones): Cicerone<Router> {
        return creator[scope] ?: throw Exception("STAS WRONG SCOPE FOR ROUTER")
    }

}