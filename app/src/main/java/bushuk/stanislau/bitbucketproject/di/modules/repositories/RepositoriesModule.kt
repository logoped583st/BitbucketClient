package bushuk.stanislau.bitbucketproject.di.modules.repositories

import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideRepositoriesModel():RepositoriesModel = RepositoriesModel()
}