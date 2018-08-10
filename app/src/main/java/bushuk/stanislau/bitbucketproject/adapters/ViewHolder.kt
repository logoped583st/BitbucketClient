package bushuk.stanislau.bitbucketproject.adapters

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import bushuk.stanislau.bitbucketproject.BR
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.user.User

class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(any: Any?, clickFollow: ClickFollow) {
        binding.setVariable(BR.data, any)
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            clickFollow.onClickItem(it, any!!)
        }
    }

}