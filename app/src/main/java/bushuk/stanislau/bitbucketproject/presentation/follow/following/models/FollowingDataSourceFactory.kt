package bushuk.stanislau.bitbucketproject.presentation.follow.following.models

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.user.User
import timber.log.Timber
import javax.inject.Inject

class FollowingDataSourceFactory : DataSource.Factory<String, User>() {

    @Inject
    lateinit var followingDataSource: FollowingDataSource

    init {
        Timber.e("INIT FABRIC")
        App.component.inject(this)
    }

    override fun create(): DataSource<String, User> {
        return followingDataSource
    }
}