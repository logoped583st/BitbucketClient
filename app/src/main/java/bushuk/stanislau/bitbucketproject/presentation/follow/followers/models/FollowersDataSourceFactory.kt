package bushuk.stanislau.bitbucketproject.presentation.follow.followers.models

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.user.User
import timber.log.Timber
import javax.inject.Inject

class FollowersDataSourceFactory : DataSource.Factory<String, User>() {

    @Inject
    lateinit var followersDataSource: FollowersDataSource

    init {
        App.component.inject(this)
    }

    override fun create(): DataSource<String, User> {
        Timber.e("CREATAE")
        return followersDataSource
    }
}