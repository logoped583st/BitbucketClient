package bushuk.stanislau.bitbucketproject.presentation.follow.following

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FollowingViewModel : BaseFollowViewModel<DataSource.Factory<String,User>>() {

    @Inject
    lateinit var router: Router

    init {
        App.component.inject(this)
    }

    fun navigateToUserScreen(userName: User) {
        router.navigateTo(Screens.USER_SCREEN, userName)
    }

    override var dataSource: BaseDataSource<User, Followers>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var dataSourceFactory: DataSource.Factory<String, User> = FollowersDataSourceFactory()


    override fun onCleared() {
        super.onCleared()
        dataSource.invalidate()
    }
}