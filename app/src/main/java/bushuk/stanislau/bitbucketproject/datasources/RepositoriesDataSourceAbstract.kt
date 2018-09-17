package bushuk.stanislau.bitbucketproject.datasources

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.global.LoadingModel
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class RepositoriesDataSourceAbstract(loadingModel: LoadingModel) : BaseDataSource<String, Repository>(loadingModel) {


    init {
        App.component.inject(this)
    }

    abstract val single: Observable<RepositoriesResponse>

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Repository>) {
        super.loadInitial(params, callback)
        compositeDisposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading(it)
                    callback.onResult(it.values, it.previous, it.next)
                }, {

                }))
    }

    abstract fun loadNextPage(url: String): Single<RepositoriesResponse>

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Repository>) {
        compositeDisposable.add(loadNextPage(params.key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.values, it.next)
                }, {

                }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Repository>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invalidate() {
        compositeDisposable.clear()
    }
}