package bushuk.stanislau.bitbucketproject.presentation.repositories

import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import javax.inject.Inject
import javax.inject.Provider


class RepositoriesDataSourceFactory @Inject constructor(
        repositoriesDataSource: Provider<out BaseDataSource<Repository, RepositoriesResponse>>)
    : BaseDataSourceFactory<Repository, RepositoriesResponse>(repositoriesDataSource)

