package bushuk.stanislau.bitbucketproject

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.utils.sharedPreferencesUtils.SharedPreferencesUtil
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
            router.newRootScreen(Screens.LOGIN_AUTH_SCREEN)
        } else {
            router.newRootScreen(Screens.MAIN_SCREEN)
        }
    }

}