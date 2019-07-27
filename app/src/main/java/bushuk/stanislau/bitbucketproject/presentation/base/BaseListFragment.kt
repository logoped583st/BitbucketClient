package bushuk.stanislau.bitbucketproject.presentation.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import bushuk.stanislau.bitbucketproject.adapters.RecyclerAdapter
import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.ItemResponse
import com.jakewharton.rxbinding2.support.v4.widget.refreshes
import kotlinx.android.synthetic.main.base_list_constraint.*

abstract class BaseListFragment<I : ItemResponse, R : BaseListResponse<I>,
        VM : BaseListLoadingViewModel<I, R>, B : ViewDataBinding>
    : BaseBindingFragment<VM, B>(), IItemClick<I> {


    private lateinit var adapter: RecyclerAdapter<I>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerAdapter(this)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter

        disposable.add(list_swiperefresh.refreshes().subscribe {
            viewModel.clearPaging()
        })

        viewModel.dataSource.observe(this, Observer(adapter::submitList))
    }

}