package bushuk.stanislau.bitbucketproject.presentation.repositories.model

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import timber.log.Timber

class RepositoriesDataSourceFactory : DataSource.Factory<String, Repository>() {

    val repositoriesDataSource: RepositoriesDataSource = RepositoriesDataSource()

    init {
        Timber.e("FACTORY CONSTRUCROT")
        App.component.inject(this)
    }

    override fun create(): DataSource<String, Repository> {
        return repositoriesDataSource
    }
}