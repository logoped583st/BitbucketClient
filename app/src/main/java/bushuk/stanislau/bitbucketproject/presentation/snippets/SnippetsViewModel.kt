package bushuk.stanislau.bitbucketproject.presentation.snippets

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.LoadingViewModel
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.presentation.snippets.models.SnippetsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet
import bushuk.stanislau.bitbucketproject.room.snippets.SnippetsResponce
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SnippetsViewModel
(val factory: SnippetsDataSourceFactory = SnippetsDataSourceFactory(),
 source: BaseDataSource<Snippet, SnippetsResponce> = factory.snippetsDataSource)
    : LoadingViewModel<Snippet, SnippetsResponce>(source) {
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