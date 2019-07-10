package bushuk.stanislau.bitbucketproject.di.modules.mainScreen

import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.FollowProtocol
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.FollowersDataSource
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.FollowersDataSourceFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FollowersModule {

    @Binds
    @DrawerScope
    abstract fun provideFollowersDataSource(followersDataSource: FollowersDataSource): FollowProtocol.IFollowDataSource

    @Binds
    @DrawerScope
    abstract fun provideFollowersDataSourceFactory(followersDataSourceFactory: FollowersDataSourceFactory): FollowersDataSourceFactory
}