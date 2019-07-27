package bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments


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
import bushuk.stanislau.bitbucketproject.databinding.FragmentPullRequestCommentsBinding
import bushuk.stanislau.bitbucketproject.presentation.base.IItemClick
import bushuk.stanislau.bitbucketproject.room.comments.Comment
import kotlinx.android.synthetic.main.fragment_pull_request_comments.*

class PullRequestCommentsFragment : Fragment(), IItemClick<Comment> {

    lateinit var viewModel: PullRequestCommentsViewModel
    lateinit var binding: FragmentPullRequestCommentsBinding

    override fun onClickItem(view: View, data: Comment) {
        val alert = AlertCodeDialog()

        alert.comment = data
        if (data.inline != null) {
            alert.show(fragmentManager, "test")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pull_request_comments, container, false)
        viewModel = ViewModelProviders.of(this).get(PullRequestCommentsViewModel::class.java)


        binding.let {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecyclerAdapter(this)
        pullreuqest_comments_screen_recycler.layoutManager = LinearLayoutManager(activity)
        pullreuqest_comments_screen_recycler.adapter = adapter
        viewModel.comments.observe(this, Observer(adapter::submitList))
    }
}
