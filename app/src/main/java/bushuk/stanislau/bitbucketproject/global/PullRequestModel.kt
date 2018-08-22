package bushuk.stanislau.bitbucketproject.global

import bushuk.stanislau.bitbucketproject.room.pullrequest.PullRequest
import io.reactivex.subjects.BehaviorSubject

class PullRequestModel {
    val publishSubject : BehaviorSubject<PullRequest> = BehaviorSubject.create()
}