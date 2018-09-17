package bushuk.stanislau.bitbucketproject.presentation.repositories.model

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.global.LoadingModel
import bushuk.stanislau.bitbucketproject.room.repositories.Repository

class RepositoriesDataSourceFactory(access: String?, language: String?, loadingModel: LoadingModel) : DataSource.Factory<String, Repository>() {

    val repositoriesDataSource: RepositoriesDataSource = RepositoriesDataSource(access, language, loadingModel)

    override fun create(): DataSource<String, Repository> {
        return repositoriesDataSource
    }
}