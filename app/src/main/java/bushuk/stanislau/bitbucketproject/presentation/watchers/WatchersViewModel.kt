package bushuk.stanislau.bitbucketproject.presentation.watchers

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.watchers.model.WatchersDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class WatchersViewModel : BaseFollowViewModel() {


    @Inject
    lateinit var watchersDataSourceFactory: WatchersDataSourceFactory


    init {
        App.component.initWatchersComponent().inject(this)
    }

    override var factory: DataSource.Factory<String, User> = watchersDataSourceFactory


    val watchers: LiveData<PagedList<User>> by lazy { LivePagedListBuilder<String, User>(watchersDataSourceFactory, Constants.listPagedConfig).build() }

    override fun onCleared() {
        super.onCleared()
        watchersDataSourceFactory.watchersDataSource.invalidate()
    }
}