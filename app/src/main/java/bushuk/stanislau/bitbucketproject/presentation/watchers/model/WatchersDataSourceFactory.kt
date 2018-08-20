package bushuk.stanislau.bitbucketproject.presentation.watchers.model

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.room.user.User


class WatchersDataSourceFactory : DataSource.Factory<String, User>() {

    val followDataSource: FollowDataSource by lazy { FollowDataSource(App.resourcesApp.getString(R.string.watchers_screen_error_text)) }

    init {
        App.component.inject(this)
    }

    override fun create(): DataSource<String, User> {
        return followDataSource
    }
}