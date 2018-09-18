package bushuk.stanislau.bitbucketproject.presentation.follow.following.models

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class FollowingDataSource : FollowDataSource() {

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var api: Api

    init {
        App.component.inject(this)
    }

    override val errorText: String = App.resourcesApp.getString(R.string.following_screen_no_following)

    override fun loadNextPage(url: String): Single<Followers> = api.getFollowersNextPage(url)

    override val single: Observable<Followers> = userModel.user.flatMapSingle { api.getFollowing(it.username) }

    override fun doOnEmpty() {
        loadingModel.errorText.postValue(App.resourcesApp.getString(R.string.following_screen_no_following))
    }

}