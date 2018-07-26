package bushuk.stanislau.bitbucketproject.presentation.followers.models

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class FollowersDataSourceFactory : DataSource.Factory<String, User>() {

    @Inject
    lateinit var followersDataSource: FollowersDataSource

    init {
        App.component.inject(this)
    }
    override fun create(): DataSource<String, User> {
        return followersDataSource
    }
}