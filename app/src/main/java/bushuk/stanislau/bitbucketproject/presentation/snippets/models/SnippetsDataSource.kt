package bushuk.stanislau.bitbucketproject.presentation.snippets.models

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.datasources.SnippetsDataSourceAbstract
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.snippets.SnippetsResponce
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SnippetsDataSource : SnippetsDataSourceAbstract() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var repositoryModel: RepositoryModel

    @Inject
    lateinit var userModel: UserModel

    init {
        App.component.inject(this)
    }

    override val single: Observable<SnippetsResponce> = userModel.user.flatMapSingle { api.getSnippets(it.username) }

    override fun loadNextPage(url: String): Single<SnippetsResponce> = api.getSnippetsNextPage(url)

    override val errorText: String = App.resourcesApp.getString(R.string.snippets_screen_no_snippets)


}