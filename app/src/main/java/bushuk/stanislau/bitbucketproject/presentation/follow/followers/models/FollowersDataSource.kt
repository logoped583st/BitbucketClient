package bushuk.stanislau.bitbucketproject.presentation.follow.followers.models

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class FollowersDataSource : FollowDataSource() {


    @Inject
    lateinit var api: Api

    @Inject
    lateinit var userModel: UserModel

    init {
        App.component.inject(this)
    }

    override val errorText: String = App.resourcesApp.getString(R.string.followers_screen_no_followers)

    override fun loadNextPage(url: String): Single<Followers> = api.getFollowersNextPage(url)

    override val single: Observable<Followers>  = userModel.user.flatMapSingle { api.getFollowers(it.username) }

    override fun doOnEmpty() {
        loadingModel.errorText.postValue(App.resourcesApp.getString(R.string.followers_screen_no_followers))
    }

}
