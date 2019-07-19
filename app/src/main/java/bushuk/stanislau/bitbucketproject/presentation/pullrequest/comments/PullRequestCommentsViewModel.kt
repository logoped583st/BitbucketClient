package bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDisposableViewModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model.PullRequestCommentsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.comments.Comment

class PullRequestCommentsViewModel(val factory: PullRequestCommentsDataSourceFactory = PullRequestCommentsDataSourceFactory())
    : BaseDisposableViewModel() {



    init {
        //App.component.inject(this)
    }

    val comments: LiveData<PagedList<Comment>> by lazy { LivePagedListBuilder(factory, Constants.listPagedConfig).build() }

}