package bushuk.stanislau.bitbucketproject.presentation.watchers.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import io.reactivex.Observable
import io.reactivex.Single

class WatchersDataSource : FollowDataSource() {

    override val errorText: String = App.resourcesApp.getString(R.string.watchers_screen_error_text)

    override fun loadNextPage(url: String): Single<Followers> = api.getFollowersNextPage(url)


    override val single: Observable<Followers> = repositoryModel.repository.switchMapSingle {
        api.getWatchersRepo(userModel.user.value.username, it.uuid)
    }

    override fun doOnEmpty() {
        loadingModel.errorText.postValue(App.resourcesApp.getString(R.string.watchers_screen_error_text))
    }
}