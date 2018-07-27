package bushuk.stanislau.bitbucketproject.presentation.followers.models

import android.arch.lifecycle.MutableLiveData

class FollowModel {

    val noFollowers: MutableLiveData<Int> = MutableLiveData()

    val loading: MutableLiveData<Int> = MutableLiveData()

    val errorText: MutableLiveData<String> = MutableLiveData()
}