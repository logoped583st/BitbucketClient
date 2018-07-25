package bushuk.stanislau.bitbucketproject.adapters

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import bushuk.stanislau.bitbucketproject.BR

class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(any: Any?) {
        binding.setVariable(BR.data, any)
        binding.executePendingBindings()
    }
}