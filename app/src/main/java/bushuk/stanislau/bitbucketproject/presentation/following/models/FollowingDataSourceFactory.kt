package bushuk.stanislau.bitbucketproject.presentation.following.models

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class FollowingDataSourceFactory : DataSource.Factory<String, User>() {

    @Inject
    lateinit var followingDataSource: FollowingDataSource

    init {
        App.component.inject(this)
    }

    override fun create(): DataSource<String, User> {
        return followingDataSource
    }
}