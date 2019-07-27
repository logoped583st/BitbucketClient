package bushuk.stanislau.bitbucketproject.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import bushuk.stanislau.bitbucketproject.BR
import bushuk.stanislau.bitbucketproject.presentation.base.IItemClick

class ViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T?, clickFollow: IItemClick<T>) {
        binding.setVariable(BR.data, item)
        binding.executePendingBindings()
        binding.root.setOnClickListener { view ->
            item?.let {
                clickFollow.onClickItem(view, item)
            }
        }
    }

}