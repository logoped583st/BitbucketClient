package bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model

import androidx.paging.DataSource
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.room.comments.Comment
import javax.inject.Inject

class PullRequestCommentsDataSourceFactory : DataSource.Factory<String, Comment>() {

    @Inject
    lateinit var pullRequestCommentsDataSource: PullRequestCommentsDataSource


    init {
        //App.component.inject(this)
    }

    override fun create(): DataSource<String, Comment> = pullRequestCommentsDataSource

}