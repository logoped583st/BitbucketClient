package bushuk.stanislau.bitbucketproject.global

import androidx.lifecycle.MutableLiveData

class LiveLoadingModel {
    val noInfo: MutableLiveData<Int> = MutableLiveData()
    val loading: MutableLiveData<Int> = MutableLiveData()
    val errorText: MutableLiveData<String> = MutableLiveData()

    fun notifyLoading(loadingModel: LoadingModel) {
        this.noInfo.postValue(loadingModel.noInfo)
        this.loading.postValue(loadingModel.loading)
        this.errorText.postValue(loadingModel.errorText)
    }
}