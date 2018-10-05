package bushuk.stanislau.bitbucketproject.presentation.watchers

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.watchers.model.WatchersDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WatchersViewModel(factory: WatchersDataSourceFactory = WatchersDataSourceFactory(),
                        val source: BaseDataSource<User, Followers> = factory.watchersDataSource)
    : BaseFollowViewModel<DataSource.Factory<String, User>>(factory, source) {

    @Inject
    lateinit var router: Router

    init {
        App.component.inject(this)
    }

    val watchers: LiveData<PagedList<User>> by lazy { LivePagedListBuilder<String, User>(factory, Constants.listPagedConfig).build() }


    override fun onCleared() {
        source.invalidate()
        super.onCleared()
    }

    fun navigateToUserScreen(userName: User) {
        router.navigateTo(Screens.USER_SCREEN, userName)
    }
}