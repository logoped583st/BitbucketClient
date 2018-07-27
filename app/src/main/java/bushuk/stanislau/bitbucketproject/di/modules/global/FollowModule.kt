package bushuk.stanislau.bitbucketproject.di.modules.global

import bushuk.stanislau.bitbucketproject.presentation.followers.models.FollowModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FollowModule {

    @Provides
    @Singleton
    fun provideFollowModel():FollowModel = FollowModel()
}