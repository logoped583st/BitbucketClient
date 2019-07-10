package bushuk.stanislau.bitbucketproject

import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.navigation.ScreensNavigator
import bushuk.stanislau.bitbucketproject.utils.preferences.ISharedPreferencesUtil
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(
        private val router: Router,
        private val tokenPreferences: ISharedPreferencesUtil)
    : ViewModel() {


    fun navigate() {
        if (tokenPreferences.getToken() == null) {
            router.newRootChain(ScreensNavigator.LoginScreen())
        } else {
            router.navigateTo(ScreensNavigator.MainScreen())
        }
    }

}