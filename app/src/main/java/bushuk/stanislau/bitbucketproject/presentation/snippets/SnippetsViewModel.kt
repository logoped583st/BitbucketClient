package bushuk.stanislau.bitbucketproject.presentation.snippets

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.presentation.base.ListLoadingViewModel
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.presentation.snippets.models.SnippetsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet
import bushuk.stanislau.bitbucketproject.room.snippets.SnippetsResponce
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions

class SnippetsViewModel
(val factory: SnippetsDataSourceFactory = SnippetsDataSourceFactory(),
 source: BaseDataSource<Snippet, SnippetsResponce> = factory.snippetsDataSource)
    : ListLoadingViewModel<Snippet, SnippetsResponce>(source) {
    override val state: LiveData<LoadingState.LoadingStateSealed<SnippetsResponce, CustomExceptions>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

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