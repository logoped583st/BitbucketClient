package bushuk.stanislau.bitbucketproject.di.modules.auth

import bushuk.stanislau.bitbucketproject.di.scopes.SimpleFragmentScope
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginRepository
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthProtocol
import dagger.Binds
import dagger.Module

@Module
abstract class AuthLoginModule {

    @Binds
    @SimpleFragmentScope
    abstract fun bindAuthLoginModel(authLoginModel: AuthLoginRepository): AuthProtocol.IAuthLoginRepository
}


