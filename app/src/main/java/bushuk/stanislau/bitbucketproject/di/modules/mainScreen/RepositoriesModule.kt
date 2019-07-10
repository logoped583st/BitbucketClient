package bushuk.stanislau.bitbucketproject.di.modules.mainScreen

import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSource
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSourceFactory
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    @DrawerScope
    fun provideRepositoriesFactory(): RepositoriesDataSourceFactory = RepositoriesDataSourceFactory()

    @Provides
    @DrawerScope
    fun provideRepositoriesData(): RepositoriesDataSource = RepositoriesDataSource()
}