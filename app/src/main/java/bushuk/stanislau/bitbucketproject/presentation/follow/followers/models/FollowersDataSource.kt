package bushuk.stanislau.bitbucketproject.presentation.follow.followers.models

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import javax.inject.Inject

class FollowersDataSource : FollowDataSource() {

    override fun onResult(value: Followers, callback: LoadCallback<String, User>) {
        callback.onResult(value.values, value.next)
    }

    override fun onResultInitial(value: Followers, callback: LoadInitialCallback<String, User>) {
        callback.onResult(value.values, value.previous, value.next)
    }

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var userModel: UserModel

    init {
        ////App.component.inject(this)
    }

    override val errorText: String = App.resourcesApp.getString(R.string.followers_screen_no_followers)

    override fun loadNextPage(url: String): Single<Followers> = api.getFollowersNextPage(url)

    override val single: Single<Followers> = api.getFollowers(userModel.user.value!!.username)

    override fun doOnEmpty() {
       // loadingModel.errorText.postValue(App.resourcesApp.getString(R.string.followers_screen_no_followers))
    }

}
