package bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.datasources.PullRequestCommentsDataSourceAbstract
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.room.comments.CommentResponse
import io.reactivex.Single
import javax.inject.Inject

class PullRequestCommentsDataSource : PullRequestCommentsDataSourceAbstract() {

    @Inject
    override
    lateinit var api: Api

    @Inject
    lateinit var pullrequestModel: PullRequestModel

    init {
        App.component.inject(this)
    }

    override val errorText: String
        get() = App.resourcesApp.getString(R.string.comments_error_text)

    override val single: Single<CommentResponse>
        get() = api.getPullRequestComments(pullrequestModel.publishSubject.value.links.comments.href)
}