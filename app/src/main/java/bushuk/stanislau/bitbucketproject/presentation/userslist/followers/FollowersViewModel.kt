package bushuk.stanislau.bitbucketproject.presentation.userslist.followers

import bushuk.stanislau.bitbucketproject.presentation.base.BaseListLoadingViewModel
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.room.user.UserResponse
import javax.inject.Inject

class FollowersViewModel @Inject constructor(followersDataSource: FollowersDataSourceFactory)
    : BaseListLoadingViewModel<User, UserResponse>(followersDataSource)