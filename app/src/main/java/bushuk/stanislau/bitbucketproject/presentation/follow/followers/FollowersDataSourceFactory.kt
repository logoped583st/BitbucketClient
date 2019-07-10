package bushuk.stanislau.bitbucketproject.presentation.follow.followers

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class FollowersDataSourceFactory @Inject constructor(private val followersDataSource: FollowersDataSource) : DataSource.Factory<String, User>() {


    override fun create(): DataSource<String, User> {
        return followersDataSource
    }

}