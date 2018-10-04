package bushuk.stanislau.bitbucketproject.presentation.follow.followers


import android.view.View
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment
import bushuk.stanislau.bitbucketproject.room.user.User

class FollowersFragment : BaseFollowFragment<FollowersViewModel>() {

    override fun onClickItem(view: View, data: Any) {
        viewModel.navigateToUserScreen(data as User)
    }

    override var viewModelClass: Class<FollowersViewModel> = FollowersViewModel::class.java
}
