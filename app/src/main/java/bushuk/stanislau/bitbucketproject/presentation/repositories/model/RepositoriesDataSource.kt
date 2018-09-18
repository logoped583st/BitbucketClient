package bushuk.stanislau.bitbucketproject.presentation.repositories.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.datasources.RepositoriesDataSourceAbstract
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RepositoriesDataSource : RepositoriesDataSourceAbstract() {

    @Inject
    lateinit var userModel: UserModel

    var url: String? = null

    @Inject
    lateinit var api: Api

    init {
        App.component.inject(this)
    }

    override val errorText: String = App.resourcesApp.getString(R.string.repositories_screen_no_repositories)

    override val single: Observable<RepositoriesResponse>
        get() = userModel.user.flatMapSingle { api.getRepos(it.username, url) }

    override fun loadNextPage(url: String): Single<RepositoriesResponse> = api.getReposNextPage(url)

    override fun invalidate() {
        compositeDisposable.clear()
    }
}