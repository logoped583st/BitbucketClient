package bushuk.stanislau.bitbucketproject.datasources

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.commits.Commit
import bushuk.stanislau.bitbucketproject.room.commits.CommitResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class CommitsDataSourceAbstract : BaseDataSource<String, Commit>() {

    init {
        App.component.inject(this)
    }

    abstract val single: Observable<CommitResponse>

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Commit>) {
        super.loadInitial(params, callback)
        compositeDisposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading(it)
                    callback.onResult(it.values, it.previous, it.next)
                }, {

                }))
    }

    abstract fun loadNextPage(url: String): Single<CommitResponse>

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Commit>) {
        compositeDisposable.add(loadNextPage(params.key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.values, it.next)
                }, {

                }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Commit>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}