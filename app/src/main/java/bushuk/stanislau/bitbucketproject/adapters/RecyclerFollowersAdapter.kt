package bushuk.stanislau.bitbucketproject.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import bushuk.stanislau.bitbucketproject.room.user.User
import timber.log.Timber

class RecyclerFollowersAdapter : PagedListAdapter<User,
        ViewHolder>(RecyclerFollowersAdapter.UserDiffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        Timber.e(super.getItemCount().toString())
        return super.getItemCount()
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