package bushuk.stanislau.bitbucketproject.di.modules.codeeditor

import bushuk.stanislau.bitbucketproject.presentation.codeeditor.CodeEditorModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CodeEditorModule {

    @Provides
    @Singleton
    fun provideCodeEditorModel():CodeEditorModel = CodeEditorModel()
}