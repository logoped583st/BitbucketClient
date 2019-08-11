package bushuk.stanislau.bitbucketproject.presentation.pullrequests


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.RecyclerScrollFab
import bushuk.stanislau.bitbucketproject.adapters.RecyclerAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentPullRequestsBinding
import bushuk.stanislau.bitbucketproject.presentation.base.IItemClick
import bushuk.stanislau.bitbucketproject.presentation.repository.RepositoryFragment
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import com.github.clans.fab.FloatingActionMenu
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.fragment_pull_requests.*
import kotlinx.android.synthetic.main.fragment_pull_requests.view.*


class PullRequestsFragment : Fragment(), IItemClick<PullRequest> {

    override fun onClickItem(view: View, data: PullRequest) {
        viewModel.navigateToPullRequestScreen(data)
    }

    lateinit var adapter: RecyclerAdapter<PullRequest>
    lateinit var binding: FragmentPullRequestsBinding
    lateinit var viewModel: PullRequestsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_pull_requests, container, false)
        viewModel = ViewModelProviders.of(this).get(PullRequestsViewModel::class.java)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.let {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
            it.fragment = this
        }

        adapter = RecyclerAdapter(this, TODO())
        pullrequests_screen_recycler.layoutManager = LinearLayoutManager(activity)
        pullrequests_screen_recycler.adapter = adapter
        pullrequests_screen_recycler.addOnScrollListener(object : RecyclerScrollFab() {
            override fun getFab(): FloatingActionMenu = binding.root.pullrequests_screen_settings_menu
        })

        stateSpinner()
        sortSpinner()
        viewModel.observeSearchView((parentFragment as RepositoryFragment).binding.pullrequestsScreenSearchView, this, adapter)
        viewModel.pullRequests.observe(this, Observer(adapter::submitList))
    }

    fun pullRequestsFilterClick(view: View) {
        binding.root.pullrequests_screen_settings_menu.close(true)
        (parentFragment as RepositoryFragment).binding.pullrequestsScreenSlidePanel.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
    }

    private fun stateSpinner() {
        val stateSpinnerAdapter: ArrayAdapter<String> = SpinnerAdapter(activity!!, android.R.layout.simple_spinner_item, mutableListOf("Open", "Merged", "Declined", "All"))
        stateSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        (parentFragment as RepositoryFragment).binding.pullrequestsScreenSpinnerState.adapter = stateSpinnerAdapter
        //  (parentFragment as RepositoryFragment).binding.pullrequestsScreenSpinnerState.setSelection(stateSpinnerAdapter.getPosition(UrlBuilder.parseState()))
        viewModel.observeStateSpinner((parentFragment as RepositoryFragment).binding.pullrequestsScreenSpinnerState, this, adapter)
    }

    private fun sortSpinner() {
        val sortSpinnerAdapter: ArrayAdapter<String> = SpinnerAdapter(activity!!, android.R.layout.simple_spinner_item,
                mutableListOf("Id up", "Id down", "Update time up", "Update time down"))
        sortSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        (parentFragment as RepositoryFragment).binding.pullrequestsScreenSpinnerSort.adapter = sortSpinnerAdapter
        // (parentFragment as RepositoryFragment).binding.pullrequestsScreenSpinnerSort.setSelection(sortSpinnerAdapter.getPosition(UrlBuilder.parseSort()))
        viewModel.observeSortSpinner((parentFragment as RepositoryFragment).binding.pullrequestsScreenSpinnerSort, this, adapter)
    }

}
