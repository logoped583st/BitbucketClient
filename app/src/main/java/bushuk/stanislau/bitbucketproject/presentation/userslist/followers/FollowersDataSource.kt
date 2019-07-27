package bushuk.stanislau.bitbucketproject.presentation.userslist.followers

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.room.user.UserResponse
import io.reactivex.Single
import javax.inject.Inject

class FollowersDataSource @Inject constructor(val api: Api, val userModel: UserModel) : BaseDataSource<User, UserResponse>() {

    override fun onResult(value: UserResponse, callback: LoadCallback<String, User>) {
        callback.onResult(value.items ?: emptyList(), value.next)
    }

    override fun onResultInitial(value: UserResponse, callback: LoadInitialCallback<String, User>) {
        callback.onResult(value.items ?: emptyList(), value.previous, value.next)
    }

    override val errorText: String = App.resourcesApp.getString(R.string.followers_screen_no_followers)

    override fun loadNextPage(url: String): Single<UserResponse> = api.getFollowersNextPage(url)

    override val single: Single<UserResponse> = userModel.user.firstOrError().flatMap {
        api.getFollowers(userModel.user.value!!.username)
    }

}
