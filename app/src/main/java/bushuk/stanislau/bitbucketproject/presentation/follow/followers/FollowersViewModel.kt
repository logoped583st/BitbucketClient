package bushuk.stanislau.bitbucketproject.presentation.follow.followers

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FollowersViewModel : BaseFollowViewModel<DataSource.Factory<String, User>>() {
    override var dataSource: BaseDataSource<User, Followers>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    @Inject
    lateinit var router: Router

    override var dataSourceFactory: DataSource.Factory<String, User> = FollowersDataSourceFactory()
//    override var dataSource: BaseDataSource<User, Followers>= todo: dataSourceFactory.followersDataSource

    init {
        App.component.inject(this)
    }

    fun navigateToUserScreen(userName: User) {
        router.navigateTo(Screens.USER_SCREEN, userName)
    }

    override fun onCleared() {
        super.onCleared()
        dataSource.invalidate()
    }


}