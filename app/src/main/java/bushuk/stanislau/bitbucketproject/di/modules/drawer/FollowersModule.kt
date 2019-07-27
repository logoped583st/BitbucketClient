package bushuk.stanislau.bitbucketproject.di.modules.drawer

import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.presentation.userslist.followers.FollowersDataSource
import bushuk.stanislau.bitbucketproject.presentation.userslist.followers.FollowersDataSourceFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FollowersModule {

    @Binds
    @DrawerScope
    abstract fun provideFollowersDataSource(followersDataSource: FollowersDataSource): FollowersDataSource

    @Binds
    @DrawerScope
    abstract fun provideFollowersDataSourceFactory(followersDataSourceFactory: FollowersDataSourceFactory): FollowersDataSourceFactory
}