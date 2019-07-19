package bushuk.stanislau.bitbucketproject.presentation.snippets

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDisposableViewModel
import bushuk.stanislau.bitbucketproject.presentation.snippets.models.SnippetsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet

class SnippetsViewModel
(val factory: SnippetsDataSourceFactory = SnippetsDataSourceFactory())
    : BaseDisposableViewModel() {


    //@Inject
  //  lateinit var router: Router

    init {
        //App.component.inject(this)
    }

    val snippets: LiveData<PagedList<Snippet>> = LivePagedListBuilder<String, Snippet>(factory, Constants.listPagedConfig).build()

    override fun onCleared() {
        super.onCleared()
        factory.snippetsDataSource.invalidate()
    }

    fun navigateToCode(url: String) {
        //router.navigateTo(Screens.SNIPPETS_CODE_SCREEN, url)
    }
}