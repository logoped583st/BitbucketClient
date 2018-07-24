package bushuk.stanislau.bitbucketproject.di.modules.auth

import bushuk.stanislau.bitbucketproject.presentation.auth.model.AuthLoginModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthLoginModule {

    @Provides
    @Singleton
    fun provideAuthLoginModel():AuthLoginModel = AuthLoginModel()
}