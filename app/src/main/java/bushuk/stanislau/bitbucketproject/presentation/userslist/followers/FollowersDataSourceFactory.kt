package bushuk.stanislau.bitbucketproject.presentation.userslist.followers

import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.room.user.UserResponse
import javax.inject.Inject
import javax.inject.Provider

class FollowersDataSourceFactory
@Inject constructor(
        followersDataSource: Provider<FollowersDataSource>)
    : BaseDataSourceFactory<User, UserResponse>(TODO())