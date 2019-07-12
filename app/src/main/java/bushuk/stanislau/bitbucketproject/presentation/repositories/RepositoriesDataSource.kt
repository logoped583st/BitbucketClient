package bushuk.stanislau.bitbucketproject.presentation.repositories

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import io.reactivex.Single
import javax.inject.Inject

class RepositoriesDataSource @Inject constructor(val api: Api, userModel: UserModel) : BaseDataSource<Repository, RepositoriesResponse>(),
        RepositoriesProtocol.IRepositoriesDataSource {

    override fun queryChange(query: String) {
        url = query
    }


    var url: String? = null

    override val errorText: String = App.resourcesApp.getString(R.string.repositories_screen_no_repositories)

    override val single: Single<RepositoriesResponse> = userModel.user.flatMapSingle { api.getRepos(it.username, url) }.firstOrError()


    override fun loadNextPage(url: String): Single<RepositoriesResponse> = api.getReposNextPage(url)

    override fun onResult(value: RepositoriesResponse, callback: LoadCallback<String, Repository>) {
        value.items?.let { callback.onResult(it, value.next) }
    }

    override fun onResultInitial(value: RepositoriesResponse, callback: LoadInitialCallback<String, Repository>) {
        value.items?.let { callback.onResult(it, value.previous, value.next) }
    }
}