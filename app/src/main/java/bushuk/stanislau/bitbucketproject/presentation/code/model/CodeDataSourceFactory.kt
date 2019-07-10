package bushuk.stanislau.bitbucketproject.presentation.code.model

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.room.code.Code
import javax.inject.Inject

class CodeDataSourceFactory : DataSource.Factory<String, Code>() {

    @Inject
    lateinit var codeDataSource: CodeDataSource

    init {
        //App.component.initCodeComponent().inject(this)
    }

    override fun create(): DataSource<String, Code> {
        return codeDataSource
    }

}