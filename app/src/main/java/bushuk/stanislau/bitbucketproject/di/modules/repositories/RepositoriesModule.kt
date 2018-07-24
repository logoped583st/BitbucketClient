package bushuk.stanislau.bitbucketproject.di.modules.repositories

import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSource
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideRepositoriesFactory():RepositoriesDataSourceFactory = RepositoriesDataSourceFactory()

    @Provides
    @Singleton
    fun provideRepositoriesData():RepositoriesDataSource = RepositoriesDataSource()
}