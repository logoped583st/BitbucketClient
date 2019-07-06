package bushuk.stanislau.bitbucketproject.di.modules.auth

import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginModel
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthProtocol
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AuthLoginModule {

    @Binds
    @Singleton
    abstract fun bindAuthLoginModel(authLoginModel: AuthLoginModel): AuthProtocol.AuthLoginRepository
}


