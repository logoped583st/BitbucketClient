package bushuk.stanislau.bitbucketproject.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesFragment
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import timber.log.Timber

class RecyclerRepositoriesAdapter : PagedListAdapter<Repository,
        ViewHolder>(UserDiffCallback){

    lateinit var clickFollow: ClickFollow



    fun setListener(baseFollow: RepositoriesFragment) {
        clickFollow = baseFollow
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),clickFollow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_recycler_repositories, parent, false)


        return ViewHolder(binding)
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem == newItem
            }
        }
    }

}