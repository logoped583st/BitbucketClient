package bushuk.stanislau.bitbucketproject.di.modules.global

import bushuk.stanislau.bitbucketproject.global.LoadingModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FollowModule {

    @Provides
    fun provideFollowModel(): LoadingModel = LoadingModel()
}