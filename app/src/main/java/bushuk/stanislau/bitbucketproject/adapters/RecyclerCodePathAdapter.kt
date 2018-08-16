package bushuk.stanislau.bitbucketproject.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.presentation.code.CodeFragment


class RecyclerCodePathAdapter(list: MutableList<String>, codeFragment: CodeFragment) : RecyclerView.Adapter<RecyclerCodePathAdapter.PathViewHolder>() {

    private var path: MutableList<String> = ArrayList()

    var listener: PathClick

    init {
        path = list
        listener = codeFragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_code_path, parent, false)
        return PathViewHolder(view, listener)
    }

    override fun getItemCount(): Int = path.size

    override fun onBindViewHolder(pathViewHolder: PathViewHolder, position: Int) {
        pathViewHolder.bind(path[position])
    }

    fun deletePath(position: Int) {

        val size = path.size
        for (pos in position + 1..path.lastIndex) {
            path.removeAt(pos)
        }

        notifyItemRangeRemoved(position+1, size)
    }

    fun changePath(string: String) {
        path.add(string)
        this.notifyItemInserted(path.size)
    }

    class PathViewHolder(itemView: View, listener: PathClick) : RecyclerView.ViewHolder(itemView) {

        fun bind(path: String) {
            (itemView as TextView).text = path
        }

        init {
            itemView.setOnClickListener {
                listener.onClickPath((itemView as TextView).text.toString(), this.adapterPosition)
            }
        }

    }

    interface PathClick {
        fun onClickPath(path: String, position: Int)
    }
}