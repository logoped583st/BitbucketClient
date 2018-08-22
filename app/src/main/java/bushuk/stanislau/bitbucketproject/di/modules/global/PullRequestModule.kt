package bushuk.stanislau.bitbucketproject.di.modules.global

import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PullRequestModule {

    @Provides
    @Singleton
    fun providePullRequestModel():PullRequestModel = PullRequestModel()
}