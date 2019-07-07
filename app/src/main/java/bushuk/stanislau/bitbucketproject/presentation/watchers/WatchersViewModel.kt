package bushuk.stanislau.bitbucketproject.presentation.watchers

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.watchers.model.WatchersDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WatchersViewModel(factory: WatchersDataSourceFactory = WatchersDataSourceFactory(),
                        val source: BaseDataSource<User, Followers> = factory.watchersDataSource)
    : BaseFollowViewModel<DataSource.Factory<String, User>>(factory, source) {
    override val state: LiveData<LoadingState.LoadingStateSealed<Followers, CustomExceptions>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    //@Inject
  //  lateinit var router: Router

    init {
        //App.component.inject(this)
    }

    val watchers: LiveData<PagedList<User>> by lazy { LivePagedListBuilder<String, User>(factory, Constants.listPagedConfig).build() }


    override fun onCleared() {
        source.invalidate()
        super.onCleared()
    }

    fun navigateToUserScreen(userName: User) {
        //router.navigateTo(Screens.USER_SCREEN, userName)
    }
}