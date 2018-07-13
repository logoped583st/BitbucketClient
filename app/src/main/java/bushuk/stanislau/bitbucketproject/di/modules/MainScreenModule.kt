package bushuk.stanislau.bitbucketproject.di.modules

import bushuk.stanislau.bitbucketproject.presentation.main.model.MainScreenModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainScreenModule {

    @Provides
    @Singleton
    fun getModel(): MainScreenModel = MainScreenModel()
}