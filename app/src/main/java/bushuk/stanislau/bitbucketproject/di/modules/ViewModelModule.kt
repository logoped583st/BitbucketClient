package bushuk.stanislau.bitbucketproject.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bushuk.stanislau.bitbucketproject.MainActivityViewModel
import bushuk.stanislau.bitbucketproject.di.ViewModelFactory
import bushuk.stanislau.bitbucketproject.di.ViewModelKey
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginViewModel
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenViewModel
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesViewModel
import bushuk.stanislau.bitbucketproject.presentation.userslist.followers.FollowersViewModel
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.multibindings.IntoMap

@Module(includes = [AndroidInjectionModule::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthLoginViewModel::class)
    abstract fun bindUserViewModel(authLoginViewModel: AuthLoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun bindMainScreenViewModel(mainScreenViewModel: MainScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun bindRepositoriesViewModel(repositoriesViewModel: RepositoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FollowersViewModel::class)
    abstract fun bindFollowersViewModel(followersViewModel: FollowersViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}