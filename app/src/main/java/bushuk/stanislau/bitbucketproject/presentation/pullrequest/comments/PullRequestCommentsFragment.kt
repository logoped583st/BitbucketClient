package bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments


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
import bushuk.stanislau.bitbucketproject.adapters.RecyclerCommentsAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentPullRequestCommentsBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.comments.Comment
import kotlinx.android.synthetic.main.fragment_pull_request_comments.*

class PullRequestCommentsFragment : Fragment(), ClickFollow {

    lateinit var viewModel: PullRequestCommentsViewModel
    lateinit var binding: FragmentPullRequestCommentsBinding

    override fun onClickItem(view: View, data: Any) {
        val alert = AlertCodeDialog()

        alert.comment = (data as Comment)
        if (data.inline != null) {
            alert.show(fragmentManager, "test")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pull_request_comments, container, false)
        viewModel = ViewModelProviders.of(this).get(PullRequestCommentsViewModel::class.java)


        binding.let {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecyclerCommentsAdapter()
        adapter.setListener(this)
        pullreuqest_comments_screen_recycler.layoutManager = LinearLayoutManager(activity)
        pullreuqest_comments_screen_recycler.adapter = adapter
        viewModel.comments.observe(this, Observer(adapter::submitList))
    }
}
