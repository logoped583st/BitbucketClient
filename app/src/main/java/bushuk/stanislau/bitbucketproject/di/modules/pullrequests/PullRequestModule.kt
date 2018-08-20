package bushuk.stanislau.bitbucketproject.di.modules.pullrequests

import bushuk.stanislau.bitbucketproject.di.scopes.PullRequestsScope
import bushuk.stanislau.bitbucketproject.presentation.pullrequests.model.PullRequestsDataSource
import bushuk.stanislau.bitbucketproject.presentation.pullrequests.model.PullRequestsDataSourceFactory
import dagger.Module
import dagger.Provides

@Module
class PullRequestModule {

    @Provides
    fun providePullRequestsDataSource(): PullRequestsDataSource = PullRequestsDataSource()

    @Provides
    @PullRequestsScope
    fun providePullRequestsDataSourceFactory(): PullRequestsDataSourceFactory = PullRequestsDataSourceFactory()
}