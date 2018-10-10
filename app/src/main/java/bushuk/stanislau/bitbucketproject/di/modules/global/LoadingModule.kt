package bushuk.stanislau.bitbucketproject.di.modules.global

import bushuk.stanislau.bitbucketproject.global.LiveLoadingModel
import bushuk.stanislau.bitbucketproject.global.LoadingModel
import dagger.Module
import dagger.Provides

@Module
class LoadingModule {

    @Provides
    fun provideLiveLoadingModel(): LiveLoadingModel = LiveLoadingModel()

    @Provides
    fun provideLoadingModel(): LoadingModel = LoadingModel()
}