package bushuk.stanislau.bitbucketproject.presentation.follow.followers.models

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.room.user.User
import javax.inject.Inject

class FollowersDataSourceFactory : DataSource.Factory<String, User>() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var followersDataSource: FollowersDataSource

    init {
        //App.component.inject(this)
    }

    override fun create(): DataSource<String, User> {
        return followersDataSource
    }

}