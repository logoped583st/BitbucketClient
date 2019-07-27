package bushuk.stanislau.bitbucketproject.presentation.watchers.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Single
import javax.inject.Inject

class WatchersDataSource : BaseDataSource<User, BaseListResponse<User>>() {

    override fun onResult(value: BaseListResponse<User>, callback: LoadCallback<String, User>) {
        callback.onResult(value.items ?: emptyList(), value.next)
    }

    override fun onResultInitial(value: BaseListResponse<User>, callback: LoadInitialCallback<String, User>) {
        callback.onResult(value.items ?: emptyList(), value.previous, value.next)
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

    override fun loadNextPage(url: String): Single<BaseListResponse<User>> =  TODO()//api.getFollowersNextPage(url)


    override val single: Single<BaseListResponse<User>> = TODO()
//            repositoryModel.repository.flatMapSingle {
//        api.getWatchersRepo(userModel.user.value!!.username, it.uuid!!)
//    }.firstOrError()

}