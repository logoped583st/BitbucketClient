package bushuk.stanislau.bitbucketproject.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.global.LiveLoadingModel
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import bushuk.stanislau.bitbucketproject.utils.extensions.mapErrors
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class ListLoadingViewModel<Value, Response>(dataSource: BaseDataSource<Value, Response>) : LoadingViewModel<Response>() {


    val liveLoadingModel: LiveLoadingModel = LiveLoadingModel()

    init {
        compositeDisposable.add(dataSource.getLoadingEventObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.e(it.toString())
                    liveLoadingModel.notifyLoading(it)
                }, {
                    Timber.e(it.message)
                }))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}


abstract class LoadingViewModel<Response> : ViewModel(), IBaseLoadingViewModel<Response> {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val loadingState = LoadingState<Response, CustomExceptions>()

    init {
        start()
    }

    private fun start() {
        loadingState.state.postValue(LoadingState.LoadingStateSealed.Start())
    }

    protected fun loading() {
        loadingState.state.postValue(LoadingState.LoadingStateSealed.Loading())
    }

    protected fun data(data: Response) {
        loadingState.state.postValue(LoadingState.LoadingStateSealed.Data(data))
    }

    protected fun error(exception: CustomExceptions) {
        loadingState.state.postValue(LoadingState.LoadingStateSealed.Error(exception))
    }

    override val state: LiveData<LoadingState.LoadingStateSealed<Response, CustomExceptions>> = loadingState.state


    protected fun Single<Response>.loadingSubscriber(
            onSuccess: (data: Response) -> Unit,
            onError: (error: Throwable) -> Unit
    ): Disposable {
        return doOnSuccess {
            data(it)
        }.doOnSubscribe {
            addDisposable(it)
            loading()
        }.mapErrors {
            error(it)
        }.subscribe({
            onSuccess(it)
        }, {
            onError(it)
        })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

interface IBaseLoadingViewModel<Response> {
    val state: LiveData<LoadingState.LoadingStateSealed<Response, CustomExceptions>>
}

fun <T> LoadingViewModel<T>.addDisposable(disposable: Disposable) {
    compositeDisposable.add(disposable)
}




