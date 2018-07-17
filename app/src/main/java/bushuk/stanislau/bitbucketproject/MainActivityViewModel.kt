package bushuk.stanislau.bitbucketproject

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.utils.TokenUtils.TokenPreferences
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.result.ResultListener
import javax.inject.Inject


class MainActivityViewModel : ViewModel(), LifecycleObserver, ResultListener {

    override fun onResult(resultData: Any?) {
        router.newRootScreen(Screens.MAIN_SCREEN)
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var tokenPreferences: TokenPreferences

    init {
        App.component.inject(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun navigate() {

        if (tokenPreferences.getToken() == null) {
            router.setResultListener(1, this)
            router.navigateTo(Screens.LOGIN_SCREEN)
        } else {
            router.newRootScreen(Screens.MAIN_SCREEN)
        }
    }


}