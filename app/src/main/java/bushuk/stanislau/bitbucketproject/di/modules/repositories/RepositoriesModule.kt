package bushuk.stanislau.bitbucketproject.di.modules.repositories

import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSource
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    fun provideRepositoriesFactory(): RepositoriesDataSourceFactory = RepositoriesDataSourceFactory()

    @Provides
    fun provideRepositoriesData(): RepositoriesDataSource = RepositoriesDataSource()
}