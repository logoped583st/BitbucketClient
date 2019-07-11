package bushuk.stanislau.bitbucketproject.presentation.watchers


import android.view.View
import androidx.lifecycle.ViewModelProvider
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject


class WatchersFragment : BaseFollowFragment<WatchersViewModel>() {


    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val clickFollow: ClickFollow<User>
        get() = this


    override fun onClickItem(view: View, data: User) {
        viewModel.navigateToUserScreen(data)
    }

    override var viewModelClass: Class<WatchersViewModel> = WatchersViewModel::class.java
}
