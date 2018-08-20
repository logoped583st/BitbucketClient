package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.di.modules.watchers.WatchersModule
import bushuk.stanislau.bitbucketproject.di.scopes.WatchersScope
import bushuk.stanislau.bitbucketproject.presentation.watchers.WatchersViewModel
import dagger.Subcomponent

@WatchersScope
@Subcomponent(modules = [WatchersModule::class])
interface WatchersComponent {

    fun inject(watchersViewModel: WatchersViewModel)
}