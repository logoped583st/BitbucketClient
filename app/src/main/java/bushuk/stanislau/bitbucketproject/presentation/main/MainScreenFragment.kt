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
import androidx.lifecycle.ViewModelProviders
import bushuk.stanislau.bitbucketproject.BackPress
import bushuk.stanislau.bitbucketproject.BackPressFragment
import bushuk.stanislau.bitbucketproject.Injectable
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.databinding.ActivityMainScreenBinding
import bushuk.stanislau.bitbucketproject.databinding.NavHeaderMainScreenBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.activity_main_screen.view.*


class MainScreenFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener, Injectable {


    private lateinit var viewModel: MainScreenViewModel

    lateinit var binding: ActivityMainScreenBinding

    private var backPress: BackPress? = null

    lateinit var navHeaderMainScreenBinding: NavHeaderMainScreenBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_main_screen, container, false)
        navHeaderMainScreenBinding = DataBindingUtil.inflate(layoutInflater, R.layout.nav_header_main_screen, binding.navView, false)
        binding.navView.addHeaderView(navHeaderMainScreenBinding.root)

        binding.root.toolbar.title = viewModel.toolbarTitle.value
        (activity as AppCompatActivity).setSupportActionBar(binding.root.toolbar)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            nav_view.menu.findItem(R.id.drawer_menu_repositories).isChecked = true
        }

        val toggle = ActionBarDrawerToggle(
                activity, drawer_layout, binding.root.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }

        navHeaderMainScreenBinding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }

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
                viewModel.drawerNavigation(Screens.REPOSITORIES_SCREEN, this.getString(R.string.toolbar_title_repository))
            }
            R.id.drawer_menu_followers -> {
                viewModel.drawerNavigation(Screens.FOLLOWERS_SCREEN, this.getString(R.string.toolbar_title_followers))
            }
            R.id.drawer_menu_following -> {
                viewModel.drawerNavigation(Screens.FOLLOWING_SCREEN, this.getString(R.string.toolbar_title_following))
            }
            R.id.drawer_menu_snippets -> {
                viewModel.drawerNavigation(Screens.SNIPPETS_SCREEN, this.getString(R.string.toolbar_title_snippets))
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
