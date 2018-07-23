package bushuk.stanislau.bitbucketproject.presentation.main.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.room.user.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.ReplaySubject
import javax.inject.Inject


class MainScreenModel {

    @Inject
    lateinit var api: Api

    init {
        App.component.inject(this)
    }

}

