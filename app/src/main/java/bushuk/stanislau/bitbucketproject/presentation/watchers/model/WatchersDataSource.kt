package bushuk.stanislau.bitbucketproject.presentation.watchers.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class WatchersDataSource : FollowDataSource() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var repositoryModel: RepositoryModel

    init {
        App.component.inject(this)
    }

    override val errorText: String = App.resourcesApp.getString(R.string.watchers_screen_error_text)

    override fun loadNextPage(url: String): Single<Followers> = api.getFollowersNextPage(url)


    override val single: Observable<Followers> = repositoryModel.repository.flatMapSingle {
        api.getWatchersRepo(userModel.user.value.username, it.uuid)
    }

    override fun doOnEmpty() {
        loadingModel.errorText.postValue(App.resourcesApp.getString(R.string.watchers_screen_error_text))
    }
}