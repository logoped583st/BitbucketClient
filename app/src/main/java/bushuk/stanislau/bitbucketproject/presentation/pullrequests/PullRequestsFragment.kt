package bushuk.stanislau.bitbucketproject.presentation.pullrequests


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerPullRequestsAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentPullRequestsBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import kotlinx.android.synthetic.main.fragment_pull_requests.*


class PullRequestsFragment : Fragment(), ClickFollow {
    override fun onClickItem(view: View, data: Any) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var adapter: RecyclerPullRequestsAdapter
    lateinit var binding: FragmentPullRequestsBinding
    lateinit var viewModel: PullRequestsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_pull_requests, container, false)

        viewModel = ViewModelProviders.of(this).get(PullRequestsViewModel::class.java)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.let {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
        }

        adapter = RecyclerPullRequestsAdapter()
        adapter.setListener(this)
        pullrequests_screen_recycler.layoutManager = LinearLayoutManager(activity)
        pullrequests_screen_recycler.adapter = adapter
        viewModel.pullRequests.observe(this, Observer(adapter::submitList))
    }
}
