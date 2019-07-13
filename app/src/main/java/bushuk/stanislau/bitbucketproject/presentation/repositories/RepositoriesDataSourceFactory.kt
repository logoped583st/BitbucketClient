package bushuk.stanislau.bitbucketproject.presentation.repositories

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import javax.inject.Inject
import javax.inject.Provider

class RepositoriesDataSourceFactory @Inject constructor(
        private val repositoriesDataSource: Provider<RepositoriesDataSource>)
    : DataSource.Factory<String, Repository>() {


    lateinit var dataSource: RepositoriesDataSource
        private set

    override fun create(): DataSource<String, Repository> {
        dataSource = repositoriesDataSource.get()

        return dataSource
    }
}