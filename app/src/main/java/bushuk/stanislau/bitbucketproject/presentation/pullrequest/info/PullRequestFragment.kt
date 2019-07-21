package bushuk.stanislau.bitbucketproject.presentation.pullrequest.info


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentPullRequestBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.ItemResponse
import bushuk.stanislau.bitbucketproject.room.user.User
import kotlinx.android.synthetic.main.fragment_pull_request.*

class PullRequestFragment : Fragment(), ClickFollow<ItemResponse> {

    override fun onClickItem(view: View, data: ItemResponse) {
        viewModel.navigateToUser(data as User)
    }

    lateinit var binding: FragmentPullRequestBinding
    lateinit var viewModel: PullRequestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pull_request, container, false)
        viewModel = ViewModelProviders.of(this).get(PullRequestViewModel::class.java)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.let {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }

        val adapterCommits = RecyclerAdapter(this)
        pullrequest_screen_recycler_commits.layoutManager = LinearLayoutManager(activity)
        pullrequest_screen_recycler_commits.adapter = adapterCommits

        val adapterReviewers = RecyclerAdapter(this)
        pullrequest_screen_recycler_reviewers.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        pullrequest_screen_recycler_reviewers.adapter = adapterReviewers

        viewModel.zipLiveData(viewModel.commits, viewModel.reviewers).observe(this, Observer {
//            adapterCommits.submitList(it!!.first)
//            adapterReviewers.submitList(it.second)
        })

    }


}