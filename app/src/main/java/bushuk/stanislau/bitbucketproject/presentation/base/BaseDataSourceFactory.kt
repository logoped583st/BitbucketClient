package bushuk.stanislau.bitbucketproject.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.global.LoadingStateSealed
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Provider

abstract class BaseDataSourceFactory<Value, Response>(private val dataSourceProvider: Provider<out BaseDataSource<Value, Response>>)
    : DataSource.Factory<String, Value>() {

    private val stateImpl = MutableLiveData<LoadingStateSealed<Response, CustomExceptions>>()

    private lateinit var dataSource: BaseDataSource<Value, Response>

    private val compositeDisposable = CompositeDisposable()

    override fun create(): DataSource<String, Value> {
        if (compositeDisposable.size() > 0) {
            compositeDisposable.clear()
        }
        dataSource = dataSourceProvider.get()

        compositeDisposable.add(dataSource.loadingStateImpl.state
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    stateImpl.postValue(it)
                })
        return dataSource
    }

    fun invalidate() {
        compositeDisposable.clear()
        dataSource.invalidate()
    }

    val state: LiveData<LoadingStateSealed<Response, CustomExceptions>> = stateImpl

}


