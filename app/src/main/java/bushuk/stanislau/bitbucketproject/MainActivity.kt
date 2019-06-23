package bushuk.stanislau.bitbucketproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject


class MainActivity : AppCompatActivity(), LifecycleOwner, Injectable, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
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

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
