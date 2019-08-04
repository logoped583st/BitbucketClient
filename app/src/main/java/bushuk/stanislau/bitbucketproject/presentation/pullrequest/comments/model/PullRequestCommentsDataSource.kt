package bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.comments.Comment
import io.reactivex.Single
import javax.inject.Inject

class PullRequestCommentsDataSource() : BaseDataSource<Comment, BaseListResponse<Comment>>() {

    override fun loadNextPage(url: String): Single<BaseListResponse<Comment>> = TODO()
    //api.getPullRequestComments(url)

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var pullRequestModel: PullRequestModel

    init {
        //App.component.inject(this)
    }

    override val errorText: String
        get() = App.resourcesApp.getString(R.string.comments_error_text)

    override val single: Single<BaseListResponse<Comment>> = TODO()
    //get() = api.getPullRequestComments(pullRequestModel.publishSubject.value!!.links.comments.href)
}