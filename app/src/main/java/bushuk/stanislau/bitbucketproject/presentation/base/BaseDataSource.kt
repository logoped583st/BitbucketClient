package bushuk.stanislau.bitbucketproject.presentation.base

import androidx.paging.PageKeyedDataSource
import bushuk.stanislau.bitbucketproject.global.LoadingStateObservable
import bushuk.stanislau.bitbucketproject.global.dataReceived
import bushuk.stanislau.bitbucketproject.global.refresh
import bushuk.stanislau.bitbucketproject.global.startLoading
import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.ItemResponse
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber


/**
 *  DO NOT APPLY SCHEDULERS FOR RX SINGLES
 *  BY DEFAULT DATASOURCE USING IO SCHEDULER FOR FETCH DATA AND MAIN FOR CALLBACK
 */

abstract class BaseDataSource<Value : ItemResponse, Response : BaseListResponse<Value>> : PageKeyedDataSource<String, Value>(),
        IBaseDataSource<Response, Value> {

    private val compositeDisposable = CompositeDisposable()

    override var isRefresh = false

    override val loadingStateImpl: LoadingStateObservable<Response, CustomExceptions> = LoadingStateObservable()

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Value>) {
        if (isRefresh) {
            loadingStateImpl.refresh()
            isRefresh = false
        } else {
            loadingStateImpl.startLoading()
        }

        compositeDisposable.add(single
                .subscribe({
                    loadingStateImpl.dataReceived(it)
                    callback.onResult(it.items ?: emptyList(), it.previous, it.next)
                }, {
                    Timber.e(it.message)
                }))
    }


    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Value>) {
        compositeDisposable.add(loadNextPage(params.key)
                .subscribe({
                    callback.onResult(it.items ?: emptyList(), it.next)
                }, {
                    Timber.e(it.message)
                }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Value>) {
        Timber.e("LOAD BEFORE")
    }

    override fun invalidate() {
        compositeDisposable.clear()
        super.invalidate()
    }
}

interface IBaseDataSource<Response : BaseListResponse<Value>, Value : ItemResponse> {

    var isRefresh: Boolean

    fun invalidate()

    val loadingStateImpl: LoadingStateObservable<Response, CustomExceptions>

    val errorText: String

    val single: Single<Response>

    fun loadNextPage(url: String): Single<Response>
}
