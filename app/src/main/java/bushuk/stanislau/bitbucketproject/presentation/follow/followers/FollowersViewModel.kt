package bushuk.stanislau.bitbucketproject.presentation.follow.followers

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FollowersViewModel(factory: FollowersDataSourceFactory = FollowersDataSourceFactory(),
                         private val source: BaseDataSource<User, Followers> = factory.followersDataSource)
    : BaseFollowViewModel<DataSource.Factory<String, User>>(factory, source) {

    @Inject
    lateinit var router: Router

    init {
        App.component.inject(this)
    }

    fun navigateToUserScreen(userName: User) {
       // router.navigateTo(Screens.USER_SCREEN, userName)
    }

    override fun onCleared() {
        super.onCleared()
        source.invalidate()
    }


}