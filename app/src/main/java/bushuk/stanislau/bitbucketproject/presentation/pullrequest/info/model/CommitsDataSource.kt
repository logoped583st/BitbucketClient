package bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.datasources.CommitsDataSourceAbstract
import bushuk.stanislau.bitbucketproject.room.commits.CommitResponse
import io.reactivex.Single

class CommitsDataSource : CommitsDataSourceAbstract() {

    override val single: Single<CommitResponse>
        get() = api.getCommitWithUrl(pullrequestModel.publishSubject.value.links.commits.href)

    override fun loadNextPage(url: String): Single<CommitResponse> = api.getCommitWithUrl(url)

    override val errorText: String = App.resourcesApp.getString(R.string.commits_error_text)

}