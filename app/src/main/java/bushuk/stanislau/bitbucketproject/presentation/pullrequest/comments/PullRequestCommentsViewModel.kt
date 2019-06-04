package bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.LoadingViewModel
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model.PullRequestCommentsDataSource
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model.PullRequestCommentsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.comments.Comment
import bushuk.stanislau.bitbucketproject.room.comments.CommentResponse
import javax.inject.Inject

class PullRequestCommentsViewModel(val factory:PullRequestCommentsDataSourceFactory = PullRequestCommentsDataSourceFactory(),
                                   source:PullRequestCommentsDataSource = factory.pullRequestCommentsDataSource) : LoadingViewModel<Comment, CommentResponse>(source) {


    init {
        App.component.inject(this)
    }

    val comments: LiveData<PagedList<Comment>> by lazy { LivePagedListBuilder(factory, Constants.listPagedConfig).build() }

}