package bushuk.stanislau.bitbucketproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import bushuk.stanislau.bitbucketproject.utils.TokenUtils.TokenPreferences
import bushuk.stanislau.bitbucketproject.utils.TokenUtils.TokenPreferencesApi23
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var tokenPreferences: TokenPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.component.inject(this)

        if (savedInstanceState == null && tokenPreferences.getToken() == null) {
            router.navigateTo(Screens.LOGIN_SCREEN)
        } else if (savedInstanceState == null) {
            router.newRootScreen(Screens.MAIN_SCREEN)
        }

        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        navigatorHolder.setNavigator(MainNavigator(this, R.id.main_container))
        super.onResume()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
