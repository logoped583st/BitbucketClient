package bushuk.stanislau.bitbucketproject

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.navigation.ScreensNavigator
import bushuk.stanislau.bitbucketproject.utils.preferences.SharedPreferencesUtil
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(val router: Router,
                                                val tokenPreferences: SharedPreferencesUtil)
    : ViewModel(), LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun navigate() {
        if (tokenPreferences.getToken() == null) {
            router.newRootScreen(ScreensNavigator.LoginScreen())
        } else {
            router.newRootScreen(ScreensNavigator.MainScreen())
        }
    }

}