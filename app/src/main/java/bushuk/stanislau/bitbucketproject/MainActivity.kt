package bushuk.stanislau.bitbucketproject

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject


class MainActivity : AppCompatActivity(), LifecycleOwner {

   // @Inject
   // lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.component.inject(this)
        val viewModel: MainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        lifecycle.addObserver(viewModel)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
 //       navigatorHolder.setNavigator(MainNavigator(this, R.id.main_container))
        super.onResume()
    }

    override fun onPause() {
 //       navigatorHolder.removeNavigator()
        super.onPause()
    }

}
