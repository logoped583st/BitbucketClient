package bushuk.stanislau.bitbucketproject.presentation.watchers.model

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject


class WatchersDataSourceFactory : DataSource.Factory<String, User>() {

    @Inject
    lateinit var watchersDataSource: WatchersDataSource

    init {
        App.component.initWatchersComponent().inject(this)
    }

    override fun create(): DataSource<String, User> {
        return watchersDataSource
    }
}