package bushuk.stanislau.bitbucketproject

import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.global.LiveLoadingModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

abstract class LoadingViewModel<Value : Any, Response : Any> : ViewModel() {

    @Inject
    lateinit var liveLoadingModel: LiveLoadingModel

    abstract val dataSource: BaseDataSource<Value, Response>

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

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