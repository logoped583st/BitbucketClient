package bushuk.stanislau.bitbucketproject.presentation.repositories

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.IUserModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class RepositoriesDataSource @Inject constructor(
        private val api: Api,
        private val queryModel: RepositoriesQueryModel,
        private val userModel: IUserModel)
    : BaseDataSource<Repository, RepositoriesResponse>(){

    init {
        Timber.e("INIT DATASOURCE")
    }

    override val errorText: String = App.resourcesApp.getString(R.string.repositories_screen_no_repositories)

    override val single: Single<RepositoriesResponse> = userModel.user
            .firstOrError()
            .flatMap { api.getRepos(it.username, queryModel.query) }

    override fun loadNextPage(url: String): Single<RepositoriesResponse> = api.getReposNextPage(url)

    override fun onResult(value: RepositoriesResponse, callback: LoadCallback<String, Repository>) {
        value.items?.let { callback.onResult(it, value.next) }
    }

    override fun onResultInitial(value: RepositoriesResponse, callback: LoadInitialCallback<String, Repository>) {
        value.items?.let { callback.onResult(it, value.previous, value.next) }
    }
}