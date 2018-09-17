package bushuk.stanislau.bitbucketproject.presentation.repositories.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.datasources.RepositoriesDataSourceAbstract
import bushuk.stanislau.bitbucketproject.global.LoadingModel
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import io.reactivex.Observable
import io.reactivex.Single

class RepositoriesDataSource(private val access: String?, private val language: String?, loadingModel: LoadingModel) : RepositoriesDataSourceAbstract(loadingModel) {

    override val errorText: String = App.resourcesApp.getString(R.string.repositories_screen_no_repositories)

    override val single: Observable<RepositoriesResponse>
        get() = userModel.user.flatMapSingle { api.getRepos(it.username, UrlBuilder.buildQuery(null, access, language)) }

    override fun loadNextPage(url: String): Single<RepositoriesResponse> = api.getReposNextPage(url)

    override fun invalidate() {
        compositeDisposable.clear()
    }
}