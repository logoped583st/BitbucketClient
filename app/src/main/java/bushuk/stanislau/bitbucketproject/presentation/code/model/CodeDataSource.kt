package bushuk.stanislau.bitbucketproject.presentation.code.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.code.Code
import bushuk.stanislau.bitbucketproject.room.code.CodeResponse
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import io.reactivex.Single
import javax.inject.Inject

class CodeDataSource : BaseDataSource<Code, CodeResponse>() {

    override fun onResult(value: CodeResponse, callback: LoadCallback<String, Code>) {
        callback.onResult(value.values, value.next)
    }

    override fun onResultInitial(value: CodeResponse, callback: LoadInitialCallback<String, Code>) {
        callback.onResult(value.values, value.previous, value.next)
    }

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var repositoryModel: RepositoryModel

    init {
        //App.component.inject(this)
    }


    override val single: Single<CodeResponse>
        get() =
            if (UrlBuilder.repositoryPath.isNullOrEmpty()) {
                api.getRepoWithName(userModel.user.value!!.username, repositoryModel.repository.value!!.name)
            } else {
                api.getRepoWithNamePath(userModel.user.value!!.username, repositoryModel.repository.value!!.name, UrlBuilder.repositoryPath!!)
            }


    override fun loadNextPage(url: String): Single<CodeResponse> = api.getRepoWithNameNextPage(url)

    override val errorText: String = App.resourcesApp.getString(R.string.code_screen_error_text)


}