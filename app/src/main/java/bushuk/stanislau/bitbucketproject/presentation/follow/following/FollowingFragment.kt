package bushuk.stanislau.bitbucketproject.presentation.follow.following


import android.view.View
import androidx.lifecycle.ViewModelProvider
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject


class FollowingFragment : BaseFollowFragment<FollowingViewModel>() {

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override var viewModelClass: Class<FollowingViewModel> = FollowingViewModel::class.java


    override val clickFollow: ClickFollow<User> = this

    override fun onClickItem(view: View, data: User) {
        viewModel.navigateToUserScreen(data)
    }


}
