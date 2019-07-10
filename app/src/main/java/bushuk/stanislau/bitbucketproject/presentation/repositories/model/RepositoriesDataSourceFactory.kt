package bushuk.stanislau.bitbucketproject.presentation.repositories.model

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.room.repositories.Repository

class RepositoriesDataSourceFactory : DataSource.Factory<String, Repository>() {

    val repositoriesDataSource: RepositoriesDataSource = RepositoriesDataSource()

    override fun create(): DataSource<String, Repository> {
        return repositoriesDataSource
    }
}