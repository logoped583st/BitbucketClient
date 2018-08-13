package bushuk.stanislau.bitbucketproject.presentation.repository


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.FragmentRepositoryBinding
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import kotlinx.android.synthetic.main.fragment_repository.view.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder


class RepositoryFragment : Fragment() {


    val navigatorHolder: NavigatorHolder = Cicerone.create().navigatorHolder

    lateinit var avatar: String
    private lateinit var repositoryName: String
    lateinit var viewModel: RepositoryViewModel
    lateinit var binding: FragmentRepositoryBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        App.component.inject(this)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository, container, false)
        viewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.let {
            it.fragment = this
            it.setLifecycleOwner(this)
        }

        getArgs()
        setToolbar(binding)
    }

    private fun setToolbar(binding: FragmentRepositoryBinding) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.root.toolbar_repository)
        (activity as AppCompatActivity).supportActionBar!!.title = repositoryName
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

    private fun getArgs() {
        avatar = arguments!!.getString("REPOSITORYAVATAR")
        repositoryName = arguments!!.getString("REPOSITORYNAME")
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onResume() {
        navigatorHolder.setNavigator(MainNavigator(activity, R.id.repository_screen_container))
        super.onResume()
    }
}
