package bushuk.stanislau.bitbucketproject.presentation.repositories.model

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import javax.inject.Inject

class RepositoriesDataSourceFactory : DataSource.Factory<String, Repository>() {

    @Inject
    lateinit var repositoriesDataSource: RepositoriesDataSource

    init {
        App.component.inject(this)
    }

    override fun create(): DataSource<String, Repository> {
        App.component.inject(this)
        return repositoriesDataSource
    }
}