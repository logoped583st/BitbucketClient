package bushuk.stanislau.bitbucketproject.presentation.follow.followers

import bushuk.stanislau.bitbucketproject.presentation.base.IBaseDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User

interface FollowProtocol {

    interface IFollowDataSource : IBaseDataSource<Followers, User>
}