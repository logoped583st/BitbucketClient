package bushuk.stanislau.bitbucketproject.di.modules.global

import bushuk.stanislau.bitbucketproject.global.UserModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserModule {

    @Provides
    @Singleton
    fun provideUserModel():UserModel = UserModel()
}