package bushuk.stanislau.bitbucketproject.presentation.base

import android.view.View
import androidx.paging.PageKeyedDataSource
import bushuk.stanislau.bitbucketproject.global.LoadingModel
import bushuk.stanislau.bitbucketproject.global.LoadingStateObservable
import bushuk.stanislau.bitbucketproject.global.dataReceived
import bushuk.stanislau.bitbucketproject.global.startLoading
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber


/**
 *  DO NOT APPLY SCHEDULERS FOR RX SINGLES
 *  BY DEFAULT DATASOURCE USING IO SCHEDULER FOR FETCH DATA AND MAIN FOR CALLBACK
 */

abstract class BaseDataSource<Value, Response> : PageKeyedDataSource<String, Value>(){

    abstract val errorText: String

    abstract val single: Single<Response>

    abstract fun loadNextPage(url: String): Single<Response>

    abstract fun onResult(value: Response, callback: LoadCallback<String, Value>)

    abstract fun onResultInitial(value: Response, callback: LoadInitialCallback<String, Value>)

    private val compositeDisposable = CompositeDisposable()

    private val loadingEvent: PublishSubject<LoadingModel> = PublishSubject.create()

    val loadingStateImpl: LoadingStateObservable<Response, CustomExceptions> = LoadingStateObservable()

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Value>) {
        loadingStateImpl.startLoading()

        compositeDisposable.add(single
                .subscribe({
                    loadingStateImpl.dataReceived(it)
                    onResultInitial(it, callback)
                }, {
                    loadingEvent.onNext(LoadingModel(noInfo = View. VISIBLE,loading = View.INVISIBLE,errorText = "Error or empty"))
                }))
    }


    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Value>) {
        compositeDisposable.add(loadNextPage(params.key)
                .subscribe({
                    onResult(it, callback)
                }, {
                    Timber.e(it.message)
                }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Value>) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invalidate() {
        compositeDisposable.clear()
        super.invalidate()
    }
}