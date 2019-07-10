package bushuk.stanislau.bitbucketproject.presentation.watchers.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import javax.inject.Inject

class WatchersDataSource : BaseDataSource<User, Followers>() {

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

    @Inject
    lateinit var repositoryModel: RepositoryModel

    init {
        //App.component.inject(this)
    }

    override val errorText: String = App.resourcesApp.getString(R.string.watchers_screen_error_text)

    override fun loadNextPage(url: String): Single<Followers> = api.getFollowersNextPage(url)


    override val single: Single<Followers> = repositoryModel.repository.flatMapSingle {
        api.getWatchersRepo(userModel.user.value!!.username, it.uuid)
    }.firstOrError()

}