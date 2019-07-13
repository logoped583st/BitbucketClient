package bushuk.stanislau.bitbucketproject.di.modules.drawer

import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesDataSource
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesDataSourceFactory
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @Binds
    @DrawerScope
    abstract fun provideRepositoriesFactory(dataSourceFactory: RepositoriesDataSourceFactory): RepositoriesDataSourceFactory

    @Binds
    @DrawerScope
    abstract fun provideRepositoriesData(dateSource: RepositoriesDataSource): RepositoriesDataSource


}