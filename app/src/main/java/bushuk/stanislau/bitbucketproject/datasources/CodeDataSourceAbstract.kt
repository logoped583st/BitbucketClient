package bushuk.stanislau.bitbucketproject.datasources

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.code.Code
import bushuk.stanislau.bitbucketproject.room.code.CodeResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class CodeDataSourceAbstract : BaseDataSource<String, Code>() {

    init {
        App.component.inject(this)
    }

    abstract val single: Observable<CodeResponse>

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Code>) {
        super.loadInitial(params, callback)
        compositeDisposable.add(single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading(it)
                    callback.onResult(it.values, it.previous, it.next)
                }, {
                    Timber.e(it.message)
                }))
    }

    abstract fun loadNextPage(url: String): Single<CodeResponse>

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Code>) {
        compositeDisposable.add(loadNextPage(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.values, it.next)
                }, {
                    Timber.e(it.message)
                }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Code>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invalidate() {
        compositeDisposable.clear()
    }
}