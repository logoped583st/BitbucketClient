package bushuk.stanislau.bitbucketproject.presentation.repository.model

import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class RepositoryModel {

    val repository: BehaviorSubject<Repository> = BehaviorSubject.create()

}