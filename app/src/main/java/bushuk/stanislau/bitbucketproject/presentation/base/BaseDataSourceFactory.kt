package bushuk.stanislau.bitbucketproject.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.global.LoadingStateSealed
import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.ItemResponse
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Provider

abstract class BaseDataSourceFactory<Value : ItemResponse, Response : BaseListResponse<Value>>
(override val dataSourceProvider: Provider<out BaseDataSource<Value, Response>>)

    : DataSource.Factory<String, Value>(), IBaseDataSourceFactory<Value, Response> {

    private val stateImpl = MutableLiveData<LoadingStateSealed<Response, CustomExceptions>>()

    private lateinit var dataSource: BaseDataSource<Value, Response>

    var isRefresh = false

    private val compositeDisposable = CompositeDisposable()


    override fun create(): DataSource<String, Value> {
        dataSource = dataSourceProvider.get()
        dataSource.isRefresh = isRefresh


        compositeDisposable.add(dataSource.loadingStateImpl.state
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    stateImpl.postValue(it)
                })

        return dataSource
    }

    override fun invalidate() {
        compositeDisposable.clear()
        dataSource.invalidate()
    }

    override val state: LiveData<LoadingStateSealed<Response, CustomExceptions>> = stateImpl

}

interface IBaseDataSourceFactory<Value : ItemResponse, Response : BaseListResponse<Value>> {

    val dataSourceProvider: Provider<out IBaseDataSource<Response, Value>>

    fun invalidate()

    fun create(): DataSource<String, Value>

    val state: LiveData<LoadingStateSealed<Response, CustomExceptions>>
}


