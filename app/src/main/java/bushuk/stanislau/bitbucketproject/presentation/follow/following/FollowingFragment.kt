package bushuk.stanislau.bitbucketproject.presentation.follow.following


import android.view.View
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.user.User


class FollowingFragment : BaseFollowFragment<FollowingViewModel>() {

    override val clickFollow: ClickFollow<User>
        get() = this

    override fun onClickItem(view: View, data: User) {
        viewModel.navigateToUserScreen(data)
    }

    override var viewModelClass: Class<FollowingViewModel> = FollowingViewModel::class.java

}
