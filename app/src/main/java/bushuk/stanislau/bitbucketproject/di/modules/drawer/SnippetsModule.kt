package bushuk.stanislau.bitbucketproject.di.modules.drawer

import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.presentation.snippets.models.SnippetsDataSource
import bushuk.stanislau.bitbucketproject.presentation.snippets.models.SnippetsDataSourceFactory
import dagger.Binds
import dagger.Module

@Module
abstract class SnippetsModule {

    @DrawerScope
    @Binds
    abstract fun provideSnippetsDataSource(snippetsDataSource: SnippetsDataSource): SnippetsDataSource

    @DrawerScope
    @Binds
    abstract fun provideSnippetsDataSourceFactory(snippetsDataSource:SnippetsDataSourceFactory): SnippetsDataSourceFactory
}
