package bushuk.stanislau.bitbucketproject.presentation.follow.followers.models

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import io.reactivex.Single

class FollowersDataSource : FollowDataSource() {

    override val single: Single<Followers> = api.getFollowers(userModel.user.value.username)

    override fun doOnEmpty() {
        errorText.postValue(App.resourcesApp.getString(R.string.followers_screen_no_followers))
    }

}
