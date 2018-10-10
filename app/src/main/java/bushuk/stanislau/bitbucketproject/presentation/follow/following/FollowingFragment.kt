package bushuk.stanislau.bitbucketproject.presentation.follow.following


import android.view.View
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment
import bushuk.stanislau.bitbucketproject.room.user.User


class FollowingFragment : BaseFollowFragment<FollowingViewModel>() {

    override fun onClickItem(view: View, data: Any) {
        viewModel.navigateToUserScreen(data as User)
    }

    override var viewModelClass: Class<FollowingViewModel> = FollowingViewModel::class.java

}
