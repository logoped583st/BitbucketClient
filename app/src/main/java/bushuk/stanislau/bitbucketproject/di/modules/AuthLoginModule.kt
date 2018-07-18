package bushuk.stanislau.bitbucketproject.di.modules

import bushuk.stanislau.bitbucketproject.presentation.baseAuth.model.AuthLoginModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthLoginModule {

    @Provides
    @Singleton
    fun provideAuthLoginModel():AuthLoginModel = AuthLoginModel()
}