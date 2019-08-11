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
import kotlinx.android.synthetic.main.fragment_repositories.*

abstract class BaseListFragment<I : ItemResponse, R : BaseListResponse<I>,
        VM : BaseListLoadingViewModel<I, R>, B : ViewDataBinding>
    : BaseBindingFragment<VM, B>(), IItemClick<I> {


    private lateinit var adapter: RecyclerAdapter<I>

    abstract val itemLayout: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerAdapter(this, itemLayout)
        list_constraint.mBinding.rv.layoutManager = LinearLayoutManager(context)
        list_constraint.mBinding.rv.adapter = adapter

        disposable.add(list_constraint.mBinding.listSwiperefresh.refreshes().subscribe {
            viewModel.clearPaging()
            viewModel.refresh()
        })

        viewModel.dataSource.observe(viewLifecycleOwner, Observer(adapter::submitList))

    }

}