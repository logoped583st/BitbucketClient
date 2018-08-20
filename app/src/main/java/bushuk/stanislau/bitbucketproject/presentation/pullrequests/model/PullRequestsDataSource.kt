package bushuk.stanislau.bitbucketproject.presentation.pullrequests.model

import android.arch.paging.PageKeyedDataSource
import android.view.View
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.LoadingModel
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PullRequestsDataSource : PageKeyedDataSource<String, PullRequest>() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var loadingModel: LoadingModel

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var repositoryModel: RepositoryModel


    override fun invalidate() {
        compositeDisposable.clear()
        super.invalidate()
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        App.component.inject(this)
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, PullRequest>) {
        compositeDisposable.add(api.getPullRequestNextPage(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.values, it.next)
                }, {
                    Timber.e(it.message)
                }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, PullRequest>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, PullRequest>) {
        loadingModel.loading.postValue(View.VISIBLE)
        loadingModel.noInfo.postValue(View.INVISIBLE)
        compositeDisposable.add(api.getPullRequests(userModel.user.value.username, repositoryModel.repository.value.uuid, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.size == 0) {
                        loadingModel.noInfo.postValue(View.VISIBLE)
                        loadingModel.errorText.postValue(App.resourcesApp.getString(R.string.pullrequests_screen_error_text))
                    }
                    callback.onResult(it.values, it.previous, it.next)
                    loadingModel.loading.postValue(View.GONE)
                }, {
                    Timber.e(it.message)
                }))
    }
}