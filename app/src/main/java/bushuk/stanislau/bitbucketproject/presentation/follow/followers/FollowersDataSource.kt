package bushuk.stanislau.bitbucketproject.presentation.follow.followers

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import javax.inject.Inject

class FollowersDataSource @Inject constructor(val api: Api, val userModel: UserModel) : BaseDataSource<User, Followers>() {

    override fun onResult(value: Followers, callback: LoadCallback<String, User>) {
        callback.onResult(value.values, value.next)
    }

    override fun onResultInitial(value: Followers, callback: LoadInitialCallback<String, User>) {
        callback.onResult(value.values, value.previous, value.next)
    }

    override val errorText: String = App.resourcesApp.getString(R.string.followers_screen_no_followers)

    override fun loadNextPage(url: String): Single<Followers> = api.getFollowersNextPage(url)

    override val single: Single<Followers> = api.getFollowers(userModel.user.value!!.username)

}
