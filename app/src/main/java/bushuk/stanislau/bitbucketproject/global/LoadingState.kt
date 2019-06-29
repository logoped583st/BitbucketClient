package bushuk.stanislau.bitbucketproject.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoadingState<DATA, ERROR> {

    val state: LiveData<LoadingState<DATA, ERROR>> = MutableLiveData<LoadingState<DATA, ERROR>>()

    sealed class LoadingState<DATA, ERROR> {
        class Loading<DATA, ERROR> : LoadingState<DATA, ERROR>()
        data class Data<DATA, ERROR>(val data: DATA) : LoadingState<DATA, ERROR>()
        data class Error<DATA, ERROR>(val error: ERROR) : LoadingState<DATA, ERROR>()
    }
}