package bushuk.stanislau.bitbucketproject.presentation.repositories

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.IUserModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.presentation.base.IBaseDataSource
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import io.reactivex.Single
import javax.inject.Inject

class RepositoriesDataSource @Inject constructor(
        private val api: Api,
        private val queryModel: IRepositoriesQueryModel,
        userModel: IUserModel)
    : BaseDataSource<Repository, RepositoriesResponse>(), IRepositoriesDataSource {


    override val errorText: String = App.resourcesApp.getString(R.string.repositories_screen_no_repositories)

    override val single: Single<RepositoriesResponse> = userModel.user
            .firstOrError()
            .flatMap { api.getRepos(it.username, queryModel.query) }

    override fun loadNextPage(url: String): Single<RepositoriesResponse> = api.getReposNextPage(url)

}


interface IRepositoriesDataSource : IBaseDataSource<RepositoriesResponse, Repository>


