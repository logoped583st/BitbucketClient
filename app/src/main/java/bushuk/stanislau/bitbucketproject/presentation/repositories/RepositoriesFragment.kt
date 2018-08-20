package bushuk.stanislau.bitbucketproject.presentation.repositories


import android.app.Activity
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import bushuk.stanislau.bitbucketproject.BackPress
import bushuk.stanislau.bitbucketproject.BackPressFragment
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerRepositoriesAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.constants.ListOfLanguages
import bushuk.stanislau.bitbucketproject.databinding.FragmentRepositoriesBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.fragment_repositories.*
import timber.log.Timber

class RepositoriesFragment : BackPressFragment(), LifecycleOwner, ClickFollow {

    lateinit var viewModel: RepositoriesViewModel
    private var test: Boolean = false
    lateinit var binding: FragmentRepositoriesBinding
    private val access: MutableList<String> = mutableListOf("All", "Public", "Private")
    private lateinit var adapter: RecyclerRepositoriesAdapter

    lateinit var string: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_repositories, container, false)
        viewModel = ViewModelProviders.of(this).get(RepositoriesViewModel::class.java)

        binding.let {
            it.fragment = this
            it.viewModelRepositories = viewModel
            it.setLifecycleOwner(this)
        }

        viewModel.language.observe(this, Observer { Timber.e(it) })

        if (savedInstanceState == null) {
            test = true
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repositories_screen_recycler.layoutManager = LinearLayoutManager(activity)
        adapter = RecyclerRepositoriesAdapter()
        adapter.setListener(this)
        accessSpinner()
        languageSpinner()
        repositories_screen_recycler.adapter = adapter

        repositories_screen_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && !repositories_screen_settings_menu.isMenuButtonHidden) {
                    repositories_screen_settings_menu.hideMenuButton(true)
                } else if (dy < 0 && repositories_screen_settings_menu.isMenuButtonHidden) {
                    repositories_screen_settings_menu.showMenuButton(true)
                }
            }
        })

        viewModel.observeSearchView(binding.repositoriesScreenSearchView, this, adapter)
        viewModel.observeLanguageChangeSpinner(binding.repositoriesScreenSpinnerLanguage, this, adapter)
        viewModel.observeAccessChangeSpinner(binding.repositoriesScreenSpinnerAccess, this, adapter)


        viewModel.repositories.observe(this, Observer(adapter::submitList))


        repositories_screen_slide_panel.setFadeOnClickListener {
            repositories_screen_slide_panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
    }

    override fun onBackPressed() {
        if ((repositories_screen_slide_panel != null)) {
            if (repositories_screen_slide_panel.panelState != SlidingUpPanelLayout.PanelState.COLLAPSED) {
                repositories_screen_slide_panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            } else {
                viewModel.exitFromFragment()
            }
        } else {
            viewModel.exitFromFragment()
        }
    }


    override fun onClickItem(view: View, data: Any) {
        viewModel.navigateToRepositoryScreen((data as Repository),viewModel.repositoriesDataSourceFactory.repositoriesDataSource.userModel.user.value.username)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity is MainScreenActivity) {
            activity.setBackPress(this)
        }
    }

    private fun languageSpinner() {
        val languageSpinnerAdapter: ArrayAdapter<String> = SpinnerAdapter(activity!!, android.R.layout.simple_spinner_item, ListOfLanguages.listOfLanguages())
        languageSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        repositories_screen_spinner_language.adapter = languageSpinnerAdapter
    }

    private fun accessSpinner() {
        val accessSpinnerAdapter: ArrayAdapter<String> = SpinnerAdapter(activity!!, android.R.layout.simple_spinner_item, access)
        accessSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        repositories_screen_spinner_access.adapter = accessSpinnerAdapter
    }

    fun clickFilterFab(view: View) {
        repositories_screen_settings_menu.close(true)
        repositories_screen_slide_panel.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
    }

}
