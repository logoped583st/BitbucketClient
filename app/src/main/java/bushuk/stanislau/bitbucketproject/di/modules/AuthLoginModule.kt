package bushuk.stanislau.bitbucketproject.di.modules

import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.di.ViewModelKey
import bushuk.stanislau.bitbucketproject.di.scopes.SimpleFragmentScope
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginRepository
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginViewModel
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthProtocol
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.multibindings.IntoMap


@Module(includes = [AndroidInjectionModule::class])
abstract class AuthLoginModule {

    @Binds
    @SimpleFragmentScope
    abstract fun bindAuthLoginModel(authLoginModel: AuthLoginRepository): AuthProtocol.IAuthLoginRepository

    @Binds
    @IntoMap
    @ViewModelKey(AuthLoginViewModel::class)
    abstract fun bindAuthLoginViewMoel(authLoginModel: AuthLoginViewModel): ViewModel

}
