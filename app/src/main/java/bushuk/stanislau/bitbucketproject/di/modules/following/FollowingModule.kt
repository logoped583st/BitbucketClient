package bushuk.stanislau.bitbucketproject.di.modules.following

import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSource
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FollowingModule {

    @Provides
    fun provideFollowingDataSource():FollowingDataSource = FollowingDataSource()

    @Provides
    @Singleton
    fun provideFollowingDataSourceFactory(): FollowingDataSourceFactory = FollowingDataSourceFactory()
}