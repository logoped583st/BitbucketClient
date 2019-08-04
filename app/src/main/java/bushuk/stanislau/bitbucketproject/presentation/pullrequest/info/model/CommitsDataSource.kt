package bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.PullRequestModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.commits.Commit
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Provider

class CommitsDataSource : BaseDataSource<Commit, BaseListResponse<Commit>>() {

    @Inject
    lateinit var pullRequestModel: PullRequestModel

    @Inject
    lateinit var api: Api

    init {
        //App.component.inject(this)
    }


    override val single: Single<BaseListResponse<Commit>>
        get() = TODO()//api.getCommitWithUrl(pullRequestModel.publishSubject.value!!.links.commits.href)

    override fun loadNextPage(url: String): Single<BaseListResponse<Commit>> = TODO()//api.getCommitWithUrl(url)

    override val errorText: String = App.resourcesApp.getString(R.string.commits_error_text)

}