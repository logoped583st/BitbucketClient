package bushuk.stanislau.bitbucketproject.di.modules.watchers

import bushuk.stanislau.bitbucketproject.di.scopes.WatchersScope
import bushuk.stanislau.bitbucketproject.presentation.watchers.model.WatchersDataSourceFactory
import dagger.Module
import dagger.Provides

@Module
class WatchersModule {

    @Provides
    @WatchersScope
    fun provideWatchersDataSourceFactory(): WatchersDataSourceFactory = WatchersDataSourceFactory()
}