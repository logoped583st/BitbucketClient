package bushuk.stanislau.bitbucketproject.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.global.*
import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.ItemResponse
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import bushuk.stanislau.bitbucketproject.utils.extensions.mapErrors
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseListLoadingViewModel<Item : ItemResponse, Response : BaseListResponse<Item>>(
        private val factory: BaseDataSourceFactory<Item, Response>)
    : BaseDisposableViewModel(), IBaseLoadingViewModel<Response> {

    override val state = factory.state

    val dataSource: LiveData<PagedList<Item>> = LivePagedListBuilder(factory, Constants.listPagedConfig)
            .build()

    fun refresh() {
        factory.isRefresh = true
    }

    fun finishRefresh() {
        factory.isRefresh = false
    }

    fun clearPaging() {
        factory.invalidate()
    }

    override fun onCleared() {
        factory.invalidate()
        super.onCleared()
    }
}

abstract class BaseLoadingViewModel<Response> : BaseDisposableViewModel(), IBaseLoadingViewModel<Response> {

    private val loadingState = LoadingStateLiveData<Response, CustomExceptions>()

    override val state: LiveData<LoadingStateSealed<Response, CustomExceptions>> = loadingState.state

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
    val state: LiveData<LoadingStateSealed<Response, CustomExceptions>>
}

fun BaseDisposableViewModel.addDisposable(disposable: Disposable) {
    compositeDisposable.add(disposable)
}




