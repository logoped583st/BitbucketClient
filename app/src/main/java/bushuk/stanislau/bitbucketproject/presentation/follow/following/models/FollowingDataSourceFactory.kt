package bushuk.stanislau.bitbucketproject.presentation.follow.following.models

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class FollowingDataSourceFactory : DataSource.Factory<String, User>() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var userModel: UserModel

    init {
        App.component.inject(this)
    }

    val followingDataSource: FollowDataSource = FollowDataSource(App.resourcesApp.getString(R.string.following_screen_no_following))

    override fun create(): DataSource<String, User> {
        return followingDataSource
    }
}