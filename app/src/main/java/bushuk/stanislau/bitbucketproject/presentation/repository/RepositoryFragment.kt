package bushuk.stanislau.bitbucketproject.presentation.repository

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.BackPressFragment
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.FragmentRepositoryBinding
import bushuk.stanislau.bitbucketproject.navigation.RepositoryNavigator
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import kotlinx.android.synthetic.main.fragment_repository.view.*
import ru.terrakok.cicerone.NavigatorHolder

class RepositoryFragment : BackPressFragment() {


    val navigatorHolder: NavigatorHolder by lazy { viewModel.cicerone.navigatorHolder }

    lateinit var viewModel: RepositoryViewModel
    lateinit var binding: FragmentRepositoryBinding
    lateinit var avatar: String
    lateinit var userName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository, container, false)
        viewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)
        avatar = arguments!!.getString("AVATAR")
        userName = arguments!!.getString("USERNAME")
        viewModel.userModel.user.value.username = userName

        binding.let {
            it.fragment = this
            it.viewModel = viewModel
            it.setLifecycleOwner(this)
        }
        setToolbar(binding)

        if (savedInstanceState == null) {
            viewModel.initView()
        }

        return binding.root
    }

    private fun setToolbar(binding: FragmentRepositoryBinding) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.root.toolbar_repository)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        viewModel.exitFromFragment()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        (activity as MainScreenActivity).recreateToolbar()
        super.onDestroyView()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        viewModel.exitFromFragment()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(RepositoryNavigator((activity as MainScreenActivity), childFragmentManager, R.id.repository_screen_container))
    }

}
