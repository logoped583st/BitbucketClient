package bushuk.stanislau.bitbucketproject.presentation.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.ActivityMainScreenBinding
import bushuk.stanislau.bitbucketproject.databinding.NavHeaderMainScreenBinding
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import bushuk.stanislau.bitbucketproject.presentation.main.viewModel.MainScreenViewModel
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.app_bar_main_screen.*
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainScreenActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.component.inject(this)
        val binding: ActivityMainScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen)
        val navHeaderMainScreenBinding: NavHeaderMainScreenBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.nav_header_main_screen, binding.navView, false)
        binding.navView.addHeaderView(navHeaderMainScreenBinding.root)
        val viewModel:MainScreenViewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)

        binding.let {
            it.viewModel = viewModel
            it.setLifecycleOwner(this)
        }

        navHeaderMainScreenBinding.let {
            it.viewModel = viewModel
            it.setLifecycleOwner(this)
        }

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_screen, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_camera -> {

            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        navigatorHolder.setNavigator(MainNavigator(this,R.id.main_screen_container))
        super.onResume()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
