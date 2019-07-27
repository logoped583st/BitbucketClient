package bushuk.stanislau.bitbucketproject.presentation.snippets.models

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet
import io.reactivex.Single
import javax.inject.Inject

class SnippetsDataSource : BaseDataSource<Snippet, BaseListResponse<Snippet>>() {

    override fun onResult(value: BaseListResponse<Snippet>, callback: LoadCallback<String, Snippet>) {
        callback.onResult(value.items ?: emptyList(), value.next)
    }

    override fun onResultInitial(value: BaseListResponse<Snippet>, callback: LoadInitialCallback<String, Snippet>) {
        callback.onResult(value.items ?: emptyList(), value.previous, value.next)
    }

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var repositoryModel: RepositoryModel

    @Inject
    lateinit var userModel: UserModel

    init {
        //App.component.inject(this)
    }

    override val single: Single<BaseListResponse<Snippet>> = TODO()
    //userModel.user.flatMapSingle { api.getSnippets(it.username) }.firstOrError()

    override fun loadNextPage(url: String): Single<BaseListResponse<Snippet>> = TODO()
    //api.getSnippetsNextPage(url)

    override val errorText: String = App.resourcesApp.getString(R.string.snippets_screen_no_snippets)


}