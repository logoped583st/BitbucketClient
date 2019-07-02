package bushuk.stanislau.bitbucketproject.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class PullRequestModel {
    val publishSubject: BehaviorSubject<PullRequest> = BehaviorSubject.create()

    init {
        val a = MutableLiveData<Int>()

        publishSubject.switchMap<PullRequest> {
            return@switchMap Observable.empty()
        }
    }



}