package bushuk.stanislau.bitbucketproject.di.modules.mainScreen

import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainScreenModule {

    @Provides
    @Singleton
    fun getModel(): MainScreenModel = MainScreenModel()
}