package bushuk.stanislau.bitbucketproject.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.ItemResponse

abstract class BaseBindingListFragment<Item : ItemResponse,
        Response : BaseListResponse<ItemResponse>,
        ViewModel : ListLoadingViewModel<Item, Response>,
        DataBinding : ViewDataBinding> : BaseBindingFragment<ViewModel, DataBinding>() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)



    }
}