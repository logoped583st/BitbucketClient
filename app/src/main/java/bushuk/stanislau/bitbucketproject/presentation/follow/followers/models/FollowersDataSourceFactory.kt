package bushuk.stanislau.bitbucketproject.presentation.follow.followers.models

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import javax.inject.Inject

class FollowersDataSourceFactory : DataSource.Factory<String, User>() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var userModel: UserModel

    init {
        App.component.inject(this)
    }

    var followersDataSource: FollowDataSource = FollowDataSource(App.resourcesApp.getString(R.string.followers_screen_no_followers))

    override fun create(): DataSource<String, User> {
        return followersDataSource
    }

}