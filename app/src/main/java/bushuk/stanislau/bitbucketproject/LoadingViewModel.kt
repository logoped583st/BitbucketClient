package bushuk.stanislau.bitbucketproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.global.LiveLoadingModel
import bushuk.stanislau.bitbucketproject.global.LoadingState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
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

    private val loadingState = LoadingState<Response, Exception>()

    fun loading() {
        loadingState.state.postValue(LoadingState.LoadingStateSealed.Loading())
    }

    fun data(data: Response) {
        loadingState.state.postValue(LoadingState.LoadingStateSealed.Data(data))
    }

    fun error(exception: Exception) {
        loadingState.state.postValue(LoadingState.LoadingStateSealed.Error(exception))
    }

    protected fun state(): LiveData<LoadingState.LoadingStateSealed<Response, Exception>> {
        return loadingState.state
    }
}

fun BaseLoadingViewModel<Any>.addDisposable(disposable: Disposable) {
    compositeDisposable.add(disposable)
}


