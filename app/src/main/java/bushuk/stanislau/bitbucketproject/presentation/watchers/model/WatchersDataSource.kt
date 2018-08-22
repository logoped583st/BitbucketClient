package bushuk.stanislau.bitbucketproject.presentation.watchers.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import io.reactivex.Single

class WatchersDataSource : FollowDataSource() {

    override val single: Single<Followers> = api.getWatchersRepo(userModel.user.value.username, repositoryModel.repository.value.uuid)


    override fun doOnEmpty() {
        errorText.postValue(App.resourcesApp.getString(R.string.watchers_screen_error_text))
    }
}