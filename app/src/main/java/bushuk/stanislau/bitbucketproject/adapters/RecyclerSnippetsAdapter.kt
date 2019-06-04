package bushuk.stanislau.bitbucketproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.presentation.snippets.SnippetsFragment
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet

class RecyclerSnippetsAdapter : PagedListAdapter<Snippet, ViewHolder>(UserDiffCallback) {

    lateinit var clickFollow: ClickFollow

    fun setListener(snippets: SnippetsFragment) {
        clickFollow = snippets
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickFollow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_recycler_snippets, parent, false)


        return ViewHolder(binding)
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<Snippet>() {
            override fun areItemsTheSame(oldItem: Snippet, newItem: Snippet): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Snippet, newItem: Snippet): Boolean {
                return oldItem == newItem
            }
        }
    }

}