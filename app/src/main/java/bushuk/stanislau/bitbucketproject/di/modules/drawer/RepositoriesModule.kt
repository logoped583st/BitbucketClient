package bushuk.stanislau.bitbucketproject.di.modules.drawer

import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesDataSource
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesProtocol
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @Binds
    @DrawerScope
    abstract fun provideRepositoriesFactory(factory: RepositoriesDataSourceFactory): RepositoriesDataSourceFactory

    @Binds
    @DrawerScope
    abstract fun provideRepositoriesData(dateSource: RepositoriesDataSource): RepositoriesProtocol.IRepositoriesDataSource
}