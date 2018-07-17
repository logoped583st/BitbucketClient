package bushuk.stanislau.bitbucketproject

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import android.arch.lifecycle.LifecycleRegistry



class MainActivity : AppCompatActivity(),LifecycleOwner {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.component.inject(this)
        val viewModel: MainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        lifecycle.addObserver(viewModel)
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
