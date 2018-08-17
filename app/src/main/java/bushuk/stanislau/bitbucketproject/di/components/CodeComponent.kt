package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.di.modules.code.CodeModule
import bushuk.stanislau.bitbucketproject.di.scopes.CodeScope
import bushuk.stanislau.bitbucketproject.presentation.code.CodeViewModel
import bushuk.stanislau.bitbucketproject.presentation.code.model.CodeDataSourceFactory
import dagger.Subcomponent

@CodeScope
@Subcomponent(modules = [CodeModule::class])
interface CodeComponent {

    fun inject(codeDataSourceFactory: CodeDataSourceFactory)

    fun inject(codeViewModel: CodeViewModel)

}