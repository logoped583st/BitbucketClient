package bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.presentation.base.ListLoadingViewModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model.PullRequestCommentsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.comments.Comment
import bushuk.stanislau.bitbucketproject.room.comments.CommentResponse
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions

class PullRequestCommentsViewModel(val factory: PullRequestCommentsDataSourceFactory = PullRequestCommentsDataSourceFactory())
    : ListLoadingViewModel<CommentResponse>() {
    override val state: LiveData<LoadingState.LoadingStateSealed<CommentResponse, CustomExceptions>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


    init {
        //App.component.inject(this)
    }

    val comments: LiveData<PagedList<Comment>> by lazy { LivePagedListBuilder(factory, Constants.listPagedConfig).build() }

}