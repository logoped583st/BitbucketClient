package bushuk.stanislau.bitbucketproject.presentation.code.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.datasources.CodeDataSourceAbstract
import bushuk.stanislau.bitbucketproject.room.code.CodeResponse
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import io.reactivex.Observable
import io.reactivex.Single

class CodeDataSource : CodeDataSourceAbstract() {

    override val single: Observable<CodeResponse> =
            if (UrlBuilder.repositoryPath.isNullOrEmpty()) {
                userModel.user
                        .switchMapSingle { api.getRepoWithName(it.username, repositoryModel.repository.value.name) }
            } else {
                userModel.user
                        .switchMapSingle { api.getRepoWithNamePath(it.username, repositoryModel.repository.value.name,UrlBuilder.repositoryPath!!) }
            }


    override fun loadNextPage(url: String): Single<CodeResponse> = api.getRepoWithNameNextPage(url)

    override val errorText: String = App.resourcesApp.getString(R.string.code_screen_error_text)

}