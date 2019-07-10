package bushuk.stanislau.bitbucketproject.presentation.repositories.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class RepositoriesDataSource : BaseDataSource<Repository, RepositoriesResponse>() {

    @Inject
    lateinit var userModel: UserModel

    var url: String? = null

    @Inject
    lateinit var api: Api

    override val errorText: String = App.resourcesApp.getString(R.string.repositories_screen_no_repositories)

    init {
        //App.component.inject(this)
    }

    override val single: Single<RepositoriesResponse> = Single.error(Exception("asd"))
            //userModel.user.flatMapSingle {  api.getRepos(it.username, url) }.firstOrError()


    override fun loadNextPage(url: String): Single<RepositoriesResponse> = api.getReposNextPage(url)

    override fun onResult(value: RepositoriesResponse, callback: LoadCallback<String, Repository>) {
        callback.onResult(value.values, value.next)
    }

    override fun onResultInitial(value: RepositoriesResponse, callback: LoadInitialCallback<String, Repository>) {
        callback.onResult(value.values, value.previous, value.next)
    }
}