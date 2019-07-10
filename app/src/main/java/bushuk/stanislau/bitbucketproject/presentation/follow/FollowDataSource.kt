package bushuk.stanislau.bitbucketproject.presentation.follow

import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User

abstract class FollowDataSource : BaseDataSource<User, Followers>() {

    abstract fun doOnEmpty()
}