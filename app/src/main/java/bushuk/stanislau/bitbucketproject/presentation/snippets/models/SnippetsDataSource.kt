package bushuk.stanislau.bitbucketproject.presentation.snippets.models

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet
import io.reactivex.Single
import javax.inject.Inject

class SnippetsDataSource @Inject constructor(val api: Api, val userModel: UserModel)
    : BaseDataSource<Snippet, BaseListResponse<Snippet>>() {


    override val single: Single<BaseListResponse<Snippet>> = userModel.user.firstOrError().flatMap { api.getSnippets(it.username) }

    override fun loadNextPage(url: String): Single<BaseListResponse<Snippet>> = TODO()
    //api.getSnippetsNextPage(url)

    override val errorText: String = App.resourcesApp.getString(R.string.snippets_screen_no_snippets)


}