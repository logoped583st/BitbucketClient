package bushuk.stanislau.bitbucketproject.adapters

import android.arch.lifecycle.MutableLiveData
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.BR
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.room.repositories.Repository


class RecyclerAdapterRepositories(private val repositoriesList: MutableLiveData<MutableList<Repository>>) : RecyclerView.Adapter<RecyclerAdapterRepositories.RepositoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RepositoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_recycler_repositories, parent, false)


        return RepositoriesViewHolder(binding)

    }


    override fun getItemCount(): Int {
        if (repositoriesList.value == null) {
            return 0
        }
        return repositoriesList.value!!.size
    }


    override fun onBindViewHolder(myViewHolder: RepositoriesViewHolder, position: Int) {
        repositoriesList.value?.get(position)?.let { myViewHolder.bind(it) }
    }


    class RepositoriesViewHolder constructor(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(any: Any) {
            binding.setVariable(BR.data, any)
            binding.executePendingBindings()
            //binding.setVariable(BR.,any)
        }
    }

}