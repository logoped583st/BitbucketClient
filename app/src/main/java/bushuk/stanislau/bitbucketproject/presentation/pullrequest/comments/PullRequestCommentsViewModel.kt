package bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model.PullRequestCommentsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.comments.Comment
import javax.inject.Inject

class PullRequestCommentsViewModel : ViewModel() {

    @Inject
    lateinit var pullRequestCommentsDataSourceFactory: PullRequestCommentsDataSourceFactory

    init {
        App.component.inject(this)
    }

    val comments: LiveData<PagedList<Comment>> by lazy { LivePagedListBuilder(pullRequestCommentsDataSourceFactory, Constants.listPagedConfig).build() }

}