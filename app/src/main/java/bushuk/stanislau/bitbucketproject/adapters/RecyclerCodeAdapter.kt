package bushuk.stanislau.bitbucketproject.adapters

import androidx.paging.PagedListAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.code.Code

class RecyclerCodeAdapter : PagedListAdapter<Code,
        ViewHolder>(UserDiffCallback) {

    lateinit var clickFollow: ClickFollow
    lateinit var holder: ViewHolder

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.holder = holder
        holder.bind(getItem(position), clickFollow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_recycler_code, parent, false)

        return ViewHolder(binding)
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<Code>() {
            override fun areItemsTheSame(oldItem: Code, newItem: Code): Boolean {
                return oldItem.path == newItem.path
            }

            override fun areContentsTheSame(oldItem: Code, newItem: Code): Boolean {
                return oldItem == newItem
            }

        }
    }
}