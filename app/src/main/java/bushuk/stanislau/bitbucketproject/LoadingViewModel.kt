package bushuk.stanislau.bitbucketproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    protected val loadingState: LiveData<LoadingState<Response, Exception>> = MutableLiveData<LoadingState<Response, Exception>>()


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
}

fun BaseLoadingViewModel<Any>.addDisposable(disposable: Disposable) {
    compositeDisposable.add(disposable)
}


