package bushuk.stanislau.bitbucketproject

import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.di.CiceroneFactory
import bushuk.stanislau.bitbucketproject.di.Cicerones
import bushuk.stanislau.bitbucketproject.navigation.ScreensNavigator
import bushuk.stanislau.bitbucketproject.utils.preferences.ISharedPreferencesUtil
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(
        routerFactory: CiceroneFactory,
        private val tokenPreferences: ISharedPreferencesUtil)
    : ViewModel() {

    private val router = routerFactory.provideCicerone(Cicerones.GLOBAL).router

    fun navigate() {
        if (tokenPreferences.getToken() == null) {
            router.newRootScreen(ScreensNavigator.LoginScreen())
        } else {
            router.newRootScreen(ScreensNavigator.MainScreen())
        }
    }

}