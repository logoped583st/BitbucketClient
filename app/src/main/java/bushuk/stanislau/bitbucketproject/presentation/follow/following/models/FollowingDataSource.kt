package bushuk.stanislau.bitbucketproject.presentation.follow.following.models

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import javax.inject.Inject

class FollowingDataSource : FollowDataSource() {
    override fun onResult(value: Followers, callback: LoadCallback<String, User>) {
        callback.onResult(value.values, value.next)
    }

    override fun onResultInitial(value: Followers, callback: LoadInitialCallback<String, User>) {
        callback.onResult(value.values, value.previous, value.next)
    }

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var api: Api

    init {
        App.component.inject(this)
    }

    override val errorText: String = App.resourcesApp.getString(R.string.following_screen_no_following)

    override fun loadNextPage(url: String): Single<Followers> = api.getFollowersNextPage(url)

    override val single: Single<Followers> = api.getFollowing(userModel.user.value.username)

    override fun doOnEmpty() {
        //loadingModel.errorText.postValue(App.resourcesApp.getString(R.string.following_screen_no_following))
    }

}