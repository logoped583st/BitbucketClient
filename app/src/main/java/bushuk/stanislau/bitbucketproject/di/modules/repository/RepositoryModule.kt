package bushuk.stanislau.bitbucketproject.di.modules.repository

import bushuk.stanislau.bitbucketproject.presentation.repository.model.RepositoryModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepositoryModel():RepositoryModel = RepositoryModel()

}