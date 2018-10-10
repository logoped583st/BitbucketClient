package bushuk.stanislau.bitbucketproject.presentation.watchers


import android.view.View
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment
import bushuk.stanislau.bitbucketproject.room.user.User


class WatchersFragment : BaseFollowFragment<WatchersViewModel>() {


    override fun onClickItem(view: View, data: Any) {
        viewModel.navigateToUserScreen(data as User)
    }

    override var viewModelClass: Class<WatchersViewModel> = WatchersViewModel::class.java
}
