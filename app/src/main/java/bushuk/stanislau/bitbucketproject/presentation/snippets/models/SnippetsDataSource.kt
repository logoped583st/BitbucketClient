package bushuk.stanislau.bitbucketproject.presentation.snippets.models

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.datasources.SnippetsDataSourceAbstract
import bushuk.stanislau.bitbucketproject.room.snippets.SnippetsResponce
import io.reactivex.Observable
import io.reactivex.Single

class SnippetsDataSource : SnippetsDataSourceAbstract() {

    override val single: Observable<SnippetsResponce> = userModel.user.switchMapSingle { api.getSnippets(it.username) }

    override fun loadNextPage(url: String): Single<SnippetsResponce> = api.getSnippetsNextPage(url)

    override val errorText: String = App.resourcesApp.getString(R.string.snippets_screen_no_snippets)


}