package bushuk.stanislau.bitbucketproject.presentation.code.model

import android.arch.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.code.Code
import javax.inject.Inject

class CodeDataSourceFactory : DataSource.Factory<String, Code>() {

    @Inject
    lateinit var codeDataSource: CodeDataSource

    init {
        App.component.initCodeComponent().inject(this)
    }

    override fun create(): DataSource<String, Code> {
        return codeDataSource
    }

}