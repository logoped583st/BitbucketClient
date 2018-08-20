package bushuk.stanislau.bitbucketproject.presentation.watchers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.watchers.model.WatchersDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.user.User
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WatchersViewModel : ViewModel() {

    @Inject
    lateinit var router:Router

    @Inject
    lateinit var watchersDataSourceFactory: WatchersDataSourceFactory

    init {
        App.component.initWatchersComponent().inject(this)
    }

    fun navigateToUserScreen(userName: User) {
        router.navigateTo(Screens.USER_SCREEN, userName)
    }

    val watchers: LiveData<PagedList<User>> by lazy { LivePagedListBuilder<String, User>(watchersDataSourceFactory, Constants.listPagedConfig).build() }

}