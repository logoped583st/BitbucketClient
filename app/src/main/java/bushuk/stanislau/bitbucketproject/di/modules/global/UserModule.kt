package bushuk.stanislau.bitbucketproject.di.modules.global

import bushuk.stanislau.bitbucketproject.global.IUserModel
import bushuk.stanislau.bitbucketproject.global.UserModel
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UserModule {

    @Binds
    @Singleton
    abstract fun provideUserModel(userModel: UserModel): IUserModel
}