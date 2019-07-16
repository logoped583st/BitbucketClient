package bushuk.stanislau.bitbucketproject.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import javax.inject.Provider

abstract class BaseDataSourceFactory<Value, Response>(private val dataSourceProvider: Provider<out BaseDataSource<Value, Response>>)
    : DataSource.Factory<String, Value>() {

    val state = MutableLiveData<LoadingState<Response, CustomExceptions>>()

    lateinit var dataSource: BaseDataSource<Value, Response>
        private set

    override fun create(): DataSource<String, Value> {
        dataSource = dataSourceProvider.get()
        state.postValue(dataSource.loadingStateImpl)

        return dataSource
    }

}

