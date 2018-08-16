package bushuk.stanislau.bitbucketproject.di.modules.code

import bushuk.stanislau.bitbucketproject.di.scopes.CodeScope
import bushuk.stanislau.bitbucketproject.presentation.code.model.CodeDataSource
import bushuk.stanislau.bitbucketproject.presentation.code.model.CodeDataSourceFactory
import dagger.Module
import dagger.Provides

@Module
class CodeModule {

    @Provides
    @CodeScope
    fun provideCodeDataSourceFactory(): CodeDataSourceFactory = CodeDataSourceFactory()

    @Provides
    @CodeScope
    fun provideCodeDataSouce(): CodeDataSource = CodeDataSource()
}