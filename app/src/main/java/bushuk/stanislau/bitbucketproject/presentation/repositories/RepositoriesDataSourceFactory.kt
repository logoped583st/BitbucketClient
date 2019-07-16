package bushuk.stanislau.bitbucketproject.presentation.repositories

import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.base.loadingSubscriber
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import javax.inject.Inject
import javax.inject.Provider

class RepositoriesDataSourceFactory @Inject constructor(
        repositoriesDataSource: Provider<RepositoriesDataSource>)
    : BaseDataSourceFactory<Repository, RepositoriesResponse>(repositoriesDataSource){
    init {
        state.loadingSubscriber()
    }
}