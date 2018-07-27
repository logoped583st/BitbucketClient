package bushuk.stanislau.bitbucketproject.di.modules.snippets

import bushuk.stanislau.bitbucketproject.presentation.snippets.models.SnippetsDataSource
import bushuk.stanislau.bitbucketproject.presentation.snippets.models.SnippetsDataSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SnippetsModule {

    @Provides
    @Singleton
    fun provideSnippetsDataSource(): SnippetsDataSource = SnippetsDataSource()

    @Provides
    @Singleton
    fun provideSnippetsDataSourceFactory(): SnippetsDataSourceFactory = SnippetsDataSourceFactory()
}