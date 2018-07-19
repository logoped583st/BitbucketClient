package bushuk.stanislau.bitbucketproject.presentation.repositories.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import javax.inject.Inject

class RepositoriesModel {

    @Inject
    lateinit var api: Api

    init {
        App.component.inject(this)
    }

    fun getOwnRepositories(user:String) = api.getRepos(user)


}