package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.di.modules.watchers.WatchersModule
import bushuk.stanislau.bitbucketproject.di.scopes.WatchersScope
import bushuk.stanislau.bitbucketproject.presentation.watchers.WatchersViewModel
import bushuk.stanislau.bitbucketproject.presentation.watchers.model.WatchersDataSource
import bushuk.stanislau.bitbucketproject.presentation.watchers.model.WatchersDataSourceFactory
import dagger.Subcomponent

@WatchersScope
@Subcomponent(modules = [WatchersModule::class])
interface WatchersComponent {

    fun inject(watchersDataSourceFactory: WatchersDataSourceFactory)

    fun inject(watchersViewModel: WatchersViewModel)
}