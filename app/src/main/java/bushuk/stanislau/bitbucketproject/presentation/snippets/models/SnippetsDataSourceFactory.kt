package bushuk.stanislau.bitbucketproject.presentation.snippets.models

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet
import javax.inject.Inject

class SnippetsDataSourceFactory : DataSource.Factory<String, Snippet>() {

    @Inject
    lateinit var snippetsDataSource: SnippetsDataSource

    init {
        App.component.inject(this)
    }

    override fun create(): DataSource<String, Snippet> {
        return snippetsDataSource
    }
}