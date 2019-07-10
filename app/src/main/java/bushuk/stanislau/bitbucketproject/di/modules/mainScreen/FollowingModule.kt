package bushuk.stanislau.bitbucketproject.di.modules.mainScreen

import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSource
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSourceFactory
import dagger.Module
import dagger.Provides

@Module
class FollowingModule {

    @Provides
    @DrawerScope
    fun provideFollowingDataSource():FollowingDataSource = FollowingDataSource()

    @Provides
    @DrawerScope
    fun provideFollowingDataSourceFactory(): FollowingDataSourceFactory = FollowingDataSourceFactory()
}