package bushuk.stanislau.bitbucketproject.presentation.watchers

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.watchers.model.WatchersDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.user.User

class WatchersViewModel(factory: WatchersDataSourceFactory = WatchersDataSourceFactory())
    : BaseFollowViewModel<DataSource.Factory<String, User>>(factory) {

    //@Inject
  //  lateinit var router: Router

    init {
        //App.component.inject(this)
    }

    val watchers: LiveData<PagedList<User>> by lazy { LivePagedListBuilder<String, User>(factory, Constants.listPagedConfig).build() }


    override fun onCleared() {
        //source.invalidate()
        super.onCleared()
    }

    fun navigateToUserScreen(userName: User) {
        //router.navigateTo(Screens.USER_SCREEN, userName)
    }
}