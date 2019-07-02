package bushuk.stanislau.bitbucketproject.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoadingState<DATA, ERROR> {

    val state = MutableLiveData<LoadingStateSealed<DATA, ERROR>>()

    sealed class LoadingStateSealed<DATA, ERROR> {
        class Loading<DATA, ERROR> : LoadingStateSealed<DATA, ERROR>()
        data class Data<DATA, ERROR>(val data: DATA) : LoadingStateSealed<DATA, ERROR>()
        data class Error<DATA, ERROR>(val error: ERROR) : LoadingStateSealed<DATA, ERROR>()
    }
}