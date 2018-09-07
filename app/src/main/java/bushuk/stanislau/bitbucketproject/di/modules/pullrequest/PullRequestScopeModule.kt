package bushuk.stanislau.bitbucketproject.di.modules.pullrequest

import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model.PullRequestCommentsDataSource
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model.PullRequestCommentsDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model.CommitsDataSource
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model.CommitsDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model.ReviewersDataSource
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model.ReviewersDataSourceFactory
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

    @Provides
    fun providePullRequestCommentsDataSource(): PullRequestCommentsDataSource = PullRequestCommentsDataSource()

    @Provides
    @Singleton
    fun providePullRequestCommentsDataSourceFactory():PullRequestCommentsDataSourceFactory = PullRequestCommentsDataSourceFactory()
}