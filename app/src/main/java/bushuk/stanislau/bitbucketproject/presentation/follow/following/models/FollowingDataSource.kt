package bushuk.stanislau.bitbucketproject.presentation.follow.following.models

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import io.reactivex.Single

class FollowingDataSource : FollowDataSource() {

    override val single: Single<Followers> = api.getFollowing(userModel.user.value.username)

    override fun doOnEmpty() {
        errorText.postValue(App.resourcesApp.getString(R.string.following_screen_no_following))
    }
}