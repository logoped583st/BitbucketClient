package bushuk.stanislau.bitbucketproject.presentation.userslist.followers

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import bushuk.stanislau.bitbucketproject.Injectable
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.FragmentRepositoriesBinding
import bushuk.stanislau.bitbucketproject.presentation.base.BaseListFragment
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.room.user.UserResponse
import javax.inject.Inject

class FollowersFragment : BaseListFragment<User, UserResponse, FollowersViewModel, FragmentRepositoriesBinding>(), Injectable {


    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val containerId: Int
        get() = R.layout.base_list_constraint

    override val scope: ViewModelScope
        get() = ViewModelScope.ACTIVITY

    override fun onClickItem(view: View, data: User) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override val viewModelClass: Class<FollowersViewModel>
        get() = FollowersViewModel::class.java

    override fun applyBinding() {
        //binding
    }

}