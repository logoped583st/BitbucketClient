package bushuk.stanislau.bitbucketproject.presentation.base

import android.view.View
import androidx.paging.PageKeyedDataSource
import bushuk.stanislau.bitbucketproject.global.LoadingModel
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.global.dataReceived
import bushuk.stanislau.bitbucketproject.global.startLoading
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

abstract class BaseDataSource<Value, Response> : PageKeyedDataSource<String, Value>(),
        IBaseDataSource<Response, Value> {

    private val compositeDisposable = CompositeDisposable()

    private val loadingEvent: PublishSubject<LoadingModel> = PublishSubject.create()

    override val loadingStateImpl: LoadingState<Response, CustomExceptions> = LoadingState()

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Value>) {
        loadingStateImpl.startLoading()

        compositeDisposable.add(single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loadingStateImpl.dataReceived(it)
                    onResultInitial(it, callback)
                }, {
                    loadingEvent.onNext(LoadingModel(noInfo = View. VISIBLE,loading = View.INVISIBLE,errorText = "Error or empty"))
                }))
    }


    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Value>) {
        compositeDisposable.add(loadNextPage(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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