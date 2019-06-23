package bushuk.stanislau.bitbucketproject.di.modules.auth

import bushuk.stanislau.bitbucketproject.presentation.auth.model.AuthLoginModel
import bushuk.stanislau.bitbucketproject.presentation.auth.model.AuthLoginRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AuthLoginModule {

    @Binds
    @Singleton
    abstract fun bindAuthLoginModel(authLoginModel: AuthLoginModel): AuthLoginRepository
}


