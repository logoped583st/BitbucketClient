package bushuk.stanislau.bitbucketproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).component.inject(this)
        router.navigateTo(Screens.LOGIN_SCREEN)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        navigatorHolder.setNavigator(MainNavigator(this,R.id.main_container))
        super.onResume()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
