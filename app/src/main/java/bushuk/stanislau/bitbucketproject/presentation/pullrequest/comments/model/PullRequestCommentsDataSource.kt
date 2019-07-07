package bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.room.comments.Comment
import bushuk.stanislau.bitbucketproject.room.comments.CommentResponse
import io.reactivex.Single
import javax.inject.Inject

class PullRequestCommentsDataSource : BaseDataSource<Comment, CommentResponse>() {
    override fun onResult(value: CommentResponse, callback: LoadCallback<String, Comment>) {
        callback.onResult(value.values, value.previous)
    }

    override fun onResultInitial(value: CommentResponse, callback: LoadInitialCallback<String, Comment>) {
        callback.onResult(value.values, value.previous, value.next)
    }

    override fun loadNextPage(url: String): Single<CommentResponse> = api.getPullRequestComments(url)

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var pullRequestModel: PullRequestModel

    init {
        //App.component.inject(this)
    }

    override val errorText: String
        get() = App.resourcesApp.getString(R.string.comments_error_text)

    override val single: Single<CommentResponse>
        get() = api.getPullRequestComments(pullRequestModel.publishSubject.value!!.links.comments.href)
}