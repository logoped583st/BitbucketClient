package bushuk.stanislau.bitbucketproject.di.modules

import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.di.ViewModelKey
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginViewModel
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.multibindings.IntoMap


@Module(includes = [AndroidInjectionModule::class])
abstract class AuthLoginModule {


    @Binds
    @IntoMap
    @ViewModelKey(AuthLoginViewModel::class)
    abstract fun bindAuthLoginViewModel(authLoginModel: AuthLoginViewModel): ViewModel

}
