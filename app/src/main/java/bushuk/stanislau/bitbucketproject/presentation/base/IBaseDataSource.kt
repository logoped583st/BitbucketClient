package bushuk.stanislau.bitbucketproject.presentation.base

import androidx.paging.PageKeyedDataSource
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import io.reactivex.Single

interface IBaseDataSource<Response, Value> {
    val errorText: String

    val single: Single<Response>

    fun loadNextPage(url: String): Single<Response>

    fun onResult(value: Response, callback: PageKeyedDataSource.LoadCallback<String, Value>)

    fun onResultInitial(value: Response, callback: PageKeyedDataSource.LoadInitialCallback<String, Value>)

    fun invalidate()

    val loadingStateImpl: LoadingState<Response, CustomExceptions>
}