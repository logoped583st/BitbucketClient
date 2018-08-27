package bushuk.stanislau.bitbucketproject.presentation.follow.followers.models

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import io.reactivex.Observable
import io.reactivex.Single

class FollowersDataSource : FollowDataSource() {

    override val errorText: String = App.resourcesApp.getString(R.string.followers_screen_no_followers)

    override fun loadNextPage(url: String): Single<Followers> = api.getFollowersNextPage(url)

    override val single: Observable<Followers> get() =  userModel.user.switchMapSingle { api.getFollowers(it.username) }

    override fun doOnEmpty() {
        loadingModel.errorText.postValue(App.resourcesApp.getString(R.string.followers_screen_no_followers))
    }

}
