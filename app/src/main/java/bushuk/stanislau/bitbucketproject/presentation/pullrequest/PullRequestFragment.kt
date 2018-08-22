package bushuk.stanislau.bitbucketproject.presentation.pullrequest


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerCommitsAdapter
import bushuk.stanislau.bitbucketproject.adapters.RecyclerReviewersAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentPullRequestBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import kotlinx.android.synthetic.main.fragment_pull_request.*
import kotlinx.android.synthetic.main.fragment_pull_request.view.*

class PullRequestFragment : Fragment(), ClickFollow {

    override fun onClickItem(view: View, data: Any) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var binding: FragmentPullRequestBinding
    lateinit var viewModel: PullRequestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pull_request, container, false)
        viewModel = ViewModelProviders.of(this).get(PullRequestViewModel::class.java)


        setToolbar(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.let {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
        }

        val adapterCommits = RecyclerCommitsAdapter()
        pullrequest_screen_recycler_commits.layoutManager = LinearLayoutManager(activity)
        pullrequest_screen_recycler_commits.adapter = adapterCommits
        adapterCommits.setListener(this)
        viewModel.commits.observe(this, Observer(adapterCommits::submitList))

        val adapterReviewers = RecyclerReviewersAdapter()
        pullrequest_screen_recycler_reviewers.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        pullrequest_screen_recycler_reviewers.adapter = adapterReviewers
        adapterReviewers.setListener(this)
        viewModel.reviewers.observe(this, Observer(adapterReviewers::submitList))
    }

    private fun setToolbar(binding: FragmentPullRequestBinding) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.root.toolbarPullrequest)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        viewModel.exitFromFragment()
        return super.onOptionsItemSelected(item)
    }
}