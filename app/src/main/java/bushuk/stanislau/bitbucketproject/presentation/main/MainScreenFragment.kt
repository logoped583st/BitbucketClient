package bushuk.stanislau.bitbucketproject.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import bushuk.stanislau.bitbucketproject.Injectable
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.ActivityMainScreenBinding
import bushuk.stanislau.bitbucketproject.databinding.NavHeaderMainScreenBinding
import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import bushuk.stanislau.bitbucketproject.navigation.ScreensNavigator
import bushuk.stanislau.bitbucketproject.presentation.base.BackPress
import bushuk.stanislau.bitbucketproject.presentation.base.BackPressFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.activity_main_screen.view.*
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

const val MAIN_SCREEN_ROUTER = "MAIN_SCREEN_ROUTER"

class MainScreenFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener, Injectable {

    @Inject
    @DrawerScope
    lateinit var mainScreenHolder: NavigatorHolder

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainScreenViewModel

    lateinit var binding: ActivityMainScreenBinding

    private var backPress: BackPress? = null

    private lateinit var navHeaderMainScreenBinding: NavHeaderMainScreenBinding

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_main_screen, container, false)
        navHeaderMainScreenBinding = DataBindingUtil.inflate(layoutInflater, R.layout.nav_header_main_screen, binding.navView, false)
        binding.navView.addHeaderView(navHeaderMainScreenBinding.root)

        (activity as AppCompatActivity).setSupportActionBar(binding.root.toolbar)

        toggle = ActionBarDrawerToggle(activity, binding.drawerLayout, binding.root.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.syncState()
        binding.drawerLayout.addDrawerListener(toggle)
        binding.navView.setNavigationItemSelectedListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(MainScreenViewModel::class.java)
        binding.root.toolbar.title = viewModel.toolbarTitle.value

        if (savedInstanceState == null) {
            binding.navView.setCheckedItem(binding.navView.menu.findItem(R.id.drawer_menu_repositories))
        }

        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }

        navHeaderMainScreenBinding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }

    }

    override fun onPause() {
        mainScreenHolder.removeNavigator()
        super.onPause()
    }

    override fun onResume() {
        mainScreenHolder.setNavigator(MainNavigator(activity, R.id.main_screen_container))
        super.onResume()
    }

    //    override fun onBackPressed() {
//        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
//            drawer_layout.closeDrawer(GravityCompat.START)
//        } else {
//            closeBottom()
//        }
//    }
//
//    private fun closeBottom() {
//        if (backPress != null) {
//            backPress!!.onBackPressed()
//        } else {
//            super.onBackPressed()
//        }
//    }

    fun setBackPress(fragment: BackPressFragment) {
        backPress = fragment
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.drawer_menu_repositories -> {
                viewModel.drawerNavigation(ScreensNavigator.RepositoriesScreen(), this.getString(R.string.toolbar_title_repository))
            }
            R.id.drawer_menu_followers -> {
                viewModel.drawerNavigation(ScreensNavigator.FollowersScreen(), this.getString(R.string.toolbar_title_followers))
            }
            R.id.drawer_menu_following -> {
                viewModel.drawerNavigation(ScreensNavigator.FollowingScreen(), this.getString(R.string.toolbar_title_following))
            }
            R.id.drawer_menu_snippets -> {
                viewModel.drawerNavigation(ScreensNavigator.SnippetsScreen(), this.getString(R.string.toolbar_title_snippets))
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


//    fun recreateToolbar() {
//        binding.root.toolbar.title = viewModel.toolbarTitle.value
//        binding.root.toolbar.menu.clear()
//        this.setSupportActionBar(binding.root.toolbar)
//        val toggle = ActionBarDrawerToggle(
//                this, drawer_layout, binding.root.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawer_layout.addDrawerListener(toggle)
//        toggle.syncState()
//        supportActionBar?.show()
//    }

}
