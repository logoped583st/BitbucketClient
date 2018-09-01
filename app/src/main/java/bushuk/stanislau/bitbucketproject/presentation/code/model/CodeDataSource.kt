package bushuk.stanislau.bitbucketproject.presentation.code.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.datasources.CodeDataSourceAbstract
import bushuk.stanislau.bitbucketproject.room.code.CodeResponse
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class CodeDataSource : CodeDataSourceAbstract() {

    override val single: Observable<CodeResponse>
        get() =
            if (UrlBuilder.repositoryPath.isNullOrEmpty()) {
                Timber.e("Rep PAth" + UrlBuilder.repositoryPath)
                userModel.user
                        .subscribeOn(Schedulers.io())
                        .flatMapSingle { api.getRepoWithName(it.username, repositoryModel.repository.value.name) }
            } else {
                userModel.user
                        .subscribeOn(Schedulers.io())
                        .flatMapSingle { api.getRepoWithNamePath(it.username, repositoryModel.repository.value.name, UrlBuilder.repositoryPath!!) }
            }


    override fun loadNextPage(url: String): Single<CodeResponse> = api.getRepoWithNameNextPage(url)

    override val errorText: String = App.resourcesApp.getString(R.string.code_screen_error_text)


}