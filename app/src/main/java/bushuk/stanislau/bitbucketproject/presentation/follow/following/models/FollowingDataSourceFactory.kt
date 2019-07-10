package bushuk.stanislau.bitbucketproject.presentation.follow.following.models

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class FollowingDataSourceFactory : DataSource.Factory<String, User>() {

    @Inject
    lateinit var followingDataSource: FollowingDataSource

    init {
        //App.component.inject(this)
    }

    override fun create(): DataSource<String, User> {
        return followingDataSource
    }

}