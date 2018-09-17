package bushuk.stanislau.bitbucketproject.datasources

import android.arch.paging.PageKeyedDataSource
import android.view.View
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.LoadingModel
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.code.CodeResponse
import bushuk.stanislau.bitbucketproject.room.followers.Followers
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequestResponse
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.snippets.SnippetsResponce
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseDataSource<Key : Any, Value : Any>(val loadingModel: LoadingModel) : PageKeyedDataSource<Key, Value>() {

    @Inject
    lateinit var userModel: UserModel


    @Inject
    lateinit var api: Api

    @Inject
    lateinit var repositoryModel: RepositoryModel

    @Inject
    lateinit var pullrequestModel: PullRequestModel

    val compositeDisposable = CompositeDisposable()

    abstract val errorText: String


    fun loading(a: Any) {
        loadingModel.loading.postValue(View.INVISIBLE)
        when (a) {
            is RepositoriesResponse -> showErrorWindow(a.size)

            is Followers -> showErrorWindow(a.size)

            is SnippetsResponce -> showErrorWindow(a.values.size)

            is CodeResponse -> showErrorWindow(a.size)

            is PullRequestResponse -> showErrorWindow(a.size)
        }
    }

    private fun showErrorWindow(size: Int) {
        if (size == 0) {
            loadingModel.noInfo.postValue(View.VISIBLE)
            loadingModel.errorText.postValue(errorText)
        }
    }


    override fun loadInitial(params: LoadInitialParams<Key>, callback: LoadInitialCallback<Key, Value>) {
        loadingModel.noInfo.postValue(View.INVISIBLE)
        loadingModel.loading.postValue(View.VISIBLE)
    }
}