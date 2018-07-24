package bushuk.stanislau.bitbucketproject.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.BR
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import timber.log.Timber


class RecyclerAdapterRepositories : PagedListAdapter<Repository,
        RecyclerAdapterRepositories.RepositoriesViewHolder>(UserDiffCallback) {

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        Timber.e(super.getItemCount().toString())
        return super.getItemCount()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RepositoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_recycler_repositories, parent, false)

        return RepositoriesViewHolder(binding)
    }


    class RepositoriesViewHolder constructor(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: Repository?) {
            binding.setVariable(BR.data, repository)
            binding.executePendingBindings()
        }
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