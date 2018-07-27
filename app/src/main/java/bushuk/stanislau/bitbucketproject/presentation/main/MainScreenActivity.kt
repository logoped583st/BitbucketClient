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
import bushuk.stanislau.bitbucketproject.Screens
import bushuk.stanislau.bitbucketproject.databinding.ActivityMainScreenBinding
import bushuk.stanislau.bitbucketproject.databinding.NavHeaderMainScreenBinding
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.activity_main_screen.view.*
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainScreenActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var viewModel: MainScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.component.inject(this)
        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)
        val binding: ActivityMainScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen)
        val navHeaderMainScreenBinding: NavHeaderMainScreenBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.nav_header_main_screen, binding.navView, false)
        binding.navView.addHeaderView(navHeaderMainScreenBinding.root)

        if (savedInstanceState == null) {
            nav_view.menu.findItem(R.id.drawer_menu_repositories).isChecked = true
            setSupportActionBar(binding.root.toolbar)
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, binding.root.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        nav_view.setNavigationItemSelectedListener(this)


        binding.let {
            it.viewModel = viewModel
            it.setLifecycleOwner(this)
        }

        navHeaderMainScreenBinding.let {
            it.viewModel = viewModel
            it.setLifecycleOwner(this)
        }


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
            R.id.drawer_menu_repositories -> {
                viewModel.drawerNavigation(Screens.REPOSITORIES_SCREEN, this.getString(R.string.toolbar_title_repository))
            }
            R.id.drawer_menu_followers -> {
                viewModel.drawerNavigation(Screens.FOLLOWERS_SCREEN, this.getString(R.string.toolbar_title_followers))
            }
            R.id.drawer_menu_following -> {
                viewModel.drawerNavigation(Screens.FOLLOWING_SCREEN,this.getString(R.string.toolbar_title_following))
            }
//            R.id.nav_manage -> {
//
//            }
//            R.id.nav_share -> {
//
//            }
//            R.id.nav_send -> {
//
//            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        navigatorHolder.setNavigator(MainNavigator(this, R.id.main_screen_container))
        super.onResume()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}
