package bushuk.stanislau.bitbucketproject.di.modules.followers

import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FollowersModule {

    @Provides
    @Singleton
    fun provideFollowersDataSourceFactory(): FollowersDataSourceFactory = FollowersDataSourceFactory()
}