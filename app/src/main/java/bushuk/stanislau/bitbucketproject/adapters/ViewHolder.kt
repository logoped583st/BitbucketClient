package bushuk.stanislau.bitbucketproject.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import bushuk.stanislau.bitbucketproject.BR
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow

class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(any: Any?, clickFollow: ClickFollow) {
        binding.setVariable(BR.data, any)
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            clickFollow.onClickItem(it, any!!)
        }
    }

}