package bushuk.stanislau.bitbucketproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.DataSource
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.user.User

class RecyclerFollowAdapter<ViewModel : BaseFollowViewModel<DataSource.Factory<String, User>>> : PagedListAdapter<User,
        ViewHolder>(UserDiffCallback) {

    lateinit var clickFollow: ClickFollow
    lateinit var holder: ViewHolder

    fun setListener(baseFollow: BaseFollowFragment<ViewModel>) {
        clickFollow = baseFollow
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.holder = holder
        holder.bind(getItem(position), clickFollow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_recycler_users, parent, false)

        return ViewHolder(binding)
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

}