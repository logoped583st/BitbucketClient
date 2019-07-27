package bushuk.stanislau.bitbucketproject.presentation.userslist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.FragmentUserListBinding
import bushuk.stanislau.bitbucketproject.presentation.base.BaseBindingFragment
import javax.inject.Inject

abstract class BaseUserListFragment<V : ViewModel> : BaseBindingFragment<V, FragmentUserListBinding>() {

    override val scope: ViewModelScope
        get() = ViewModelScope.ACTIVITY

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory


    override val containerId: Int
        get() = R.layout.fragment_user_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}