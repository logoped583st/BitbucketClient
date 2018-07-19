package bushuk.stanislau.bitbucketproject.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.R


class RecyclerAdapterRepositories(val list: List<Any>) : RecyclerView.Adapter<RecyclerAdapterRepositories.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding:ViewDataBinding = DataBindingUtil.inflate(layoutInflater,R.layout.item_recycler_repositories,parent,false)

        return  MyViewHolder(binding)

    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
       // myViewHolder.bind(list[position])
      //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class MyViewHolder constructor(val itemView: ViewDataBinding) : RecyclerView.ViewHolder(itemView.root) {

//        fun bind(data: Any) {
//            //binding.setVariable()
//            binding.executePendingBindings()
//        }
    }
}