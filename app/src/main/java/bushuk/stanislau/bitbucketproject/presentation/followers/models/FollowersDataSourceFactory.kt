package bushuk.stanislau.bitbucketproject.presentation.followers.models

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.room.user.User

class FollowersDataSourceFactory : DataSource.Factory<String, User>() {

    override fun create(): DataSource<String, User> {
        return FollowersDataSource()
    }
}