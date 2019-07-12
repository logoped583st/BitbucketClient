package bushuk.stanislau.bitbucketproject.presentation.repositories

import bushuk.stanislau.bitbucketproject.presentation.base.IBaseDataSource
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository

interface RepositoriesProtocol {

    interface IRepositoriesDataSource : IBaseDataSource<RepositoriesResponse, Repository> {

        fun queryChange(query: String)

    }
}