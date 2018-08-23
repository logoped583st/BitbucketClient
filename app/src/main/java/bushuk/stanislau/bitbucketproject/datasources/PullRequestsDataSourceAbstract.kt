package bushuk.stanislau.bitbucketproject.datasources

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequestResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class PullRequestsDataSourceAbstract : BaseDataSource<String, PullRequest>() {

    init {
        App.component.inject(this)
    }

    abstract val single: Observable<PullRequestResponse>

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, PullRequest>) {
        super.loadInitial(params, callback)
        compositeDisposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading(it)
                    callback.onResult(it.values, it.previous, it.next)
                }, {

                }))
    }

    abstract fun loadNextPage(url: String): Single<PullRequestResponse>

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, PullRequest>) {
        compositeDisposable.add(loadNextPage(params.key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.values, it.next)
                }, {

                }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, PullRequest>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

