package bushuk.stanislau.bitbucketproject.presentation.repositories

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import javax.inject.Inject

class RepositoriesDataSourceFactory @Inject constructor(private val repositoriesDataSource: RepositoriesDataSource)
    : DataSource.Factory<String, Repository>() {


    override fun create(): DataSource<String, Repository> {
        return repositoriesDataSource
    }
}