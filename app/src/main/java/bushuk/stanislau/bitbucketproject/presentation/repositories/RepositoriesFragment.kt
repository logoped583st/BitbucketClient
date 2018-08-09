package bushuk.stanislau.bitbucketproject.presentation.repositories


import android.app.Activity
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.BackPress
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerRepositoriesAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentRepositoriesBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.fragment_repositories.*

class RepositoriesFragment : Fragment(), LifecycleOwner, ClickFollow, BackPress {

    lateinit var viewModel: RepositoriesViewModel
    private var test: Boolean = false
    lateinit var binding: FragmentRepositoriesBinding

    private lateinit var adapter: RecyclerRepositoriesAdapter

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

        if (savedInstanceState == null) {
            viewModel.repositoriesDataSourceFactory.repositoriesDataSource.query = HashMap()
        }

        viewModel.observeSearchView(binding.repositoriesScreenSearchView, this, adapter)

        viewModel.repositories.observe(this, Observer(adapter::submitList))
    }

    override fun onBackPressed() {
        if (repositories_screen_slide_panel.panelState != SlidingUpPanelLayout.PanelState.COLLAPSED) {
            repositories_screen_slide_panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        } else {
            activity!!.finish()
        }
    }

    

    override fun onClickItem(view: View, data: Any) {
        if (repositories_screen_slide_panel.panelState != SlidingUpPanelLayout.PanelState.COLLAPSED) {
            repositories_screen_slide_panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        (activity as MainScreenActivity).setBackPress(this)
    }
}
