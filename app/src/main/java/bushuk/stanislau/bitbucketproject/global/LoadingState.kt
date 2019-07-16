package bushuk.stanislau.bitbucketproject.global

import androidx.lifecycle.MutableLiveData
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions

class LoadingState<DATA, ERROR> {

    val state = MutableLiveData<LoadingStateSealed<DATA, ERROR>>()

    init {
        state.postValue(LoadingStateSealed.Start())
    }

    sealed class LoadingStateSealed<DATA, ERROR> {
        internal class Start<DATA, ERROR> : LoadingStateSealed<DATA, ERROR>()
        class Loading<DATA, ERROR> : LoadingStateSealed<DATA, ERROR>()
        data class Data<DATA, ERROR>(val data: DATA) : LoadingStateSealed<DATA, ERROR>()
        data class Error<DATA, ERROR>(val error: ERROR) : LoadingStateSealed<DATA, ERROR>()
    }


}

fun <T, C : CustomExceptions> LoadingState<T, C>.startLoading() {
    state.postValue(LoadingState.LoadingStateSealed.Loading())
}

fun <T, C : CustomExceptions> LoadingState<T, C>.dataReceived(data: T) {
    state.postValue(LoadingState.LoadingStateSealed.Data(data))
}

fun <T, C : CustomExceptions> LoadingState<T, C>.onError(error: C) {
    state.postValue(LoadingState.LoadingStateSealed.Error(error))
}