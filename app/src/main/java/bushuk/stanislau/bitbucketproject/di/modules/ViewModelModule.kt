package bushuk.stanislau.bitbucketproject.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bushuk.stanislau.bitbucketproject.MainActivityViewModel
import bushuk.stanislau.bitbucketproject.di.ViewModelKey
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginViewModel
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
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}