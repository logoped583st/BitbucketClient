package bushuk.stanislau.bitbucketproject.presentation.codeeditor

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.api.ScalarApi
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import io.reactivex.Single
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CodeEditorModel {

    @Inject
    lateinit var scalarApi: ScalarApi

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var repositoryModel: RepositoryModel

    fun getCode(): Single<String> = scalarApi.getCodeOfFile(userModel.user.value.username, repositoryModel.repository.value.name, UrlBuilder.repositoryPath!!)

    init {
        App.component.inject(this)
    }

}