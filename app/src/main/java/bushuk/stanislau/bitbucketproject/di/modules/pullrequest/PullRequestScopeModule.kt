package bushuk.stanislau.bitbucketproject.di.modules.pullrequest

import bushuk.stanislau.bitbucketproject.presentation.pullrequest.model.CommitsDataSource
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.model.CommitsDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.model.ReviewersDataSource
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.model.ReviewersDataSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PullRequestScopeModule {

    @Provides
    @Singleton
    fun provideCommitsDataSourceFactory(): CommitsDataSourceFactory = CommitsDataSourceFactory()

    @Provides
    fun provideCommitsDataSource(): CommitsDataSource = CommitsDataSource()

    @Provides
    @Singleton
    fun provideReviewersDataSourceFactory(): ReviewersDataSourceFactory = ReviewersDataSourceFactory()

    @Provides
    fun provideReviewersDataSource(): ReviewersDataSource = ReviewersDataSource()
}