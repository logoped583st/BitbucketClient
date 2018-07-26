package bushuk.stanislau.bitbucketproject.di.modules.followers

import bushuk.stanislau.bitbucketproject.presentation.followers.models.FollowersDataSource
import bushuk.stanislau.bitbucketproject.presentation.followers.models.FollowersDataSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FollowersModule {

    @Provides
    @Singleton
    fun provideFollowersDataSource(): FollowersDataSource = FollowersDataSource()

    @Provides
    @Singleton
    fun provideFollowersDataSourceFactory(): FollowersDataSourceFactory = FollowersDataSourceFactory()
}