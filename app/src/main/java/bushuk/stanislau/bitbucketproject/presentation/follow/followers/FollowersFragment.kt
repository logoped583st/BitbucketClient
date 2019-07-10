package bushuk.stanislau.bitbucketproject.presentation.follow.followers


import android.view.View
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.user.User

class FollowersFragment : BaseFollowFragment<FollowersViewModel>() {

    override val clickFollow: ClickFollow<User>
        get() = this

    override fun onClickItem(view: View, data: User) {
        viewModel.navigateToUserScreen(data)

    }


    override var viewModelClass: Class<FollowersViewModel> = FollowersViewModel::class.java
}
