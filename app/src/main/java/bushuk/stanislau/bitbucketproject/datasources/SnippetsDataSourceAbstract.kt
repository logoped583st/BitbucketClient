package bushuk.stanislau.bitbucketproject.datasources

import android.arch.paging.PageKeyedDataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet
import bushuk.stanislau.bitbucketproject.room.snippets.SnippetsResponce
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class SnippetsDataSourceAbstract : BaseDataSource<String, Snippet>() {

    init {
        App.component.inject(this)
    }

    abstract val single: Observable<SnippetsResponce>

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<String>, callback: PageKeyedDataSource.LoadInitialCallback<String, Snippet>) {
        super.loadInitial(params, callback)
        compositeDisposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading(it)
                    callback.onResult(it.values, it.previous, it.next)
                }, {

                }))
    }

    abstract fun loadNextPage(url: String): Single<SnippetsResponce>

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<String>, callback: PageKeyedDataSource.LoadCallback<String, Snippet>) {
        compositeDisposable.add(loadNextPage(params.key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.values, it.next)
                }, {

                }))
    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<String>, callback: PageKeyedDataSource.LoadCallback<String, Snippet>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}