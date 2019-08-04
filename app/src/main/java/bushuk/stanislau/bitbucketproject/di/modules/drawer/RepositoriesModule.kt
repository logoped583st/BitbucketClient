package bushuk.stanislau.bitbucketproject.di.modules.drawer

import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.di.ViewModelKey
import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.presentation.repositories.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module(includes = [AndroidInjectionModule::class, RepositoriesModule.RepositoriesDataSourceModule::class])
abstract class RepositoriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun bindRepositoriesViewModel(repositoriesViewModel: RepositoriesViewModel): ViewModel

    @Binds
    @DrawerScope
    abstract fun bindRepositoriesQeryModel(repositoriesQueryModel: RepositoriesQueryModel): IRepositoriesQueryModel

    @Binds
    @DrawerScope
    abstract fun bindRepositoriesDataSource(repositoriesDataSource: RepositoriesDataSource): IRepositoriesDataSource

    @Module
    class RepositoriesDataSourceModule {

        @Provides
        fun bindRepositoriesFactory(repositoriesDataSource: Provider<RepositoriesDataSource>): RepositoriesDataSourceFactory {
            return RepositoriesDataSourceFactory(repositoriesDataSource)
        }
    }

}


