package bushuk.stanislau.bitbucketproject.di.modules.drawer

import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.di.ViewModelKey
import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.presentation.team.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module(includes = [AndroidInjectionModule::class, TeamsModule.TeamsDataSourceModule::class])
abstract class TeamsModule {
    @Binds
    @IntoMap
    @ViewModelKey(TeamsViewModel::class)
    abstract fun bindTeamsViewModel(teamsViewModel: TeamsViewModel): ViewModel

    @Binds
    @DrawerScope
    abstract fun bindTeamsQueryModel(teamsQueryModel: TeamsQueryModel): ITeamsQueryModel

    @Binds
    @DrawerScope
    abstract fun bindTeamsDataSource(teamsDataSource: TeamsDataSource): ITeamsDataSource

    @Module
    class TeamsDataSourceModule {

        @Provides
        fun bindTeamsFactory(teamsDataSource: Provider<TeamsDataSource>): TeamsDataSourceFactory {
            return TeamsDataSourceFactory(teamsDataSource)
        }
    }
}