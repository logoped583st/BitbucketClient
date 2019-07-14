package bushuk.stanislau.bitbucketproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import bushuk.stanislau.bitbucketproject.di.CiceroneFactory
import bushuk.stanislau.bitbucketproject.di.Cicerones
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), LifecycleOwner, Injectable, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var navigatorHolder: CiceroneFactory

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
            viewModel.navigate()
        }
        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.provideCicerone(Cicerones.GLOBAL).navigatorHolder.setNavigator(MainNavigator(this, supportFragmentManager, R.id.main_container))
    }

    override fun onPause() {
        navigatorHolder.provideCicerone(Cicerones.GLOBAL).navigatorHolder.removeNavigator()
        super.onPause()
    }


    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
