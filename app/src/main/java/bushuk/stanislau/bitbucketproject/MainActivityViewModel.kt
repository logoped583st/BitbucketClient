package bushuk.stanislau.bitbucketproject

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.utils.preferences.SharedPreferencesUtil
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class MainActivityViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var tokenPreferences: SharedPreferencesUtil

    init {
        App.component.inject(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun navigate() {
        if (tokenPreferences.getToken() == null) {
//
        } else {
            //router.newRootScreen(Screens.MAIN_SCREEN)
        }
    }

}