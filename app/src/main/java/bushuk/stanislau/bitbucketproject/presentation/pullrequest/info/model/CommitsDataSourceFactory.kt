package bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.commits.Commit
import javax.inject.Inject

class CommitsDataSourceFactory : DataSource.Factory<String, Commit>() {

    @Inject
    lateinit var commitsDataSource: CommitsDataSource

    init {
        App.component.inject(this)
    }


    override fun create(): DataSource<String, Commit> {
        return commitsDataSource
    }
}