package bushuk.stanislau.bitbucketproject.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.global.*
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import bushuk.stanislau.bitbucketproject.utils.extensions.mapErrors
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class ListLoadingViewModel<Response>
    : BaseDisposableViewModel(), IBaseLoadingViewModel<Response> {

    val liveLoadingModel: LiveLoadingModel = LiveLoadingModel()


}


abstract class LoadingViewModel<Response> : BaseDisposableViewModel(), IBaseLoadingViewModel<Response> {

    private val loadingState = LoadingState<Response, CustomExceptions>()


    override val state: LiveData<LoadingState.LoadingStateSealed<Response, CustomExceptions>> = loadingState.state


    protected fun Single<Response>.loadingSubscriber(
            onSuccess: (data: Response) -> Unit,
            onError: (error: Throwable) -> Unit
    ): Disposable {
        return doOnSuccess {
            loadingState.dataReceived(it)
        }.doOnSubscribe {
            addDisposable(it)
            loadingState.startLoading()
        }.mapErrors {
            loadingState.onError(it)
        }.subscribe({
            onSuccess(it)
        }, {
            onError(it)
        })
    }

}

abstract class BaseDisposableViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

interface IBaseLoadingViewModel<Response> {
    val state: LiveData<LoadingState.LoadingStateSealed<Response, CustomExceptions>>
}

fun BaseDisposableViewModel.addDisposable(disposable: Disposable) {
    compositeDisposable.add(disposable)
}

fun <Response> LiveData<out LoadingState<Response, CustomExceptions>>.loadingSubscriber()
        : LiveData<LoadingState.LoadingStateSealed<Response, CustomExceptions>> {
    val result = MediatorLiveData<LoadingState.LoadingStateSealed<Response, CustomExceptions>>()


    result.addSource(this) { data ->
        data.state.value?.let {
            result.postValue(it)
        }
    }
    return result
}


