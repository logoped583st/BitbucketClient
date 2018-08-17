package bushuk.stanislau.bitbucketproject.global

import android.arch.lifecycle.MutableLiveData

class LoadingModel {

    val noInfo: MutableLiveData<Int> = MutableLiveData()

    val loading: MutableLiveData<Int> = MutableLiveData()

    val errorText: MutableLiveData<String> = MutableLiveData()
}