package bushuk.stanislau.bitbucketproject.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.PullRequestFragment
import bushuk.stanislau.bitbucketproject.room.commits.Commit

class RecyclerCommitsAdapter : PagedListAdapter<Commit,
        ViewHolder>(UserDiffCallback) {

    lateinit var clickFollow: ClickFollow
    lateinit var holder: ViewHolder

    fun setListener(baseFollow: PullRequestFragment) {
        clickFollow = baseFollow
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.holder = holder
        holder.bind(getItem(position), clickFollow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_recycler_commit, parent, false)

        return ViewHolder(binding)
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<Commit>() {
            override fun areItemsTheSame(oldItem: Commit, newItem: Commit): Boolean {
                return oldItem.hash == newItem.hash
            }

            override fun areContentsTheSame(oldItem: Commit, newItem: Commit): Boolean {
                return oldItem == newItem
            }
        }
    }

}