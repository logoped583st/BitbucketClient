package bushuk.stanislau.bitbucketproject

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
import java.util.*
import javax.inject.Inject

abstract class LoadingViewModel<Value, Response>(dataSource: BaseDataSource<Value, Response>) : BaseLoadingViewModel<Response>() {

    @Inject
    lateinit var liveLoadingModel: LiveLoadingModel

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


abstract class BaseLoadingViewModel<Response> : ViewModel() {
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

    fun state(): LiveData<LoadingState.LoadingStateSealed<Response, CustomExceptions>> {
        return loadingState.state
    }

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

fun <T> BaseLoadingViewModel<T>.addDisposable(disposable: Disposable) {
    compositeDisposable.add(disposable)
}


