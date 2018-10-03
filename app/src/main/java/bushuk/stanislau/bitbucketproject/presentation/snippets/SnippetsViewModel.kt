package bushuk.stanislau.bitbucketproject.presentation.snippets

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.BaseDataSource
import bushuk.stanislau.bitbucketproject.LoadingViewModel
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.constants.Screens
import bushuk.stanislau.bitbucketproject.presentation.snippets.models.SnippetsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet
import bushuk.stanislau.bitbucketproject.room.snippets.SnippetsResponce
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SnippetsViewModel : LoadingViewModel<Snippet,SnippetsResponce>() {


    @Inject
    lateinit var snippetsDataSourceFactory: SnippetsDataSourceFactory

    @Inject
    lateinit var router: Router


    init {
        App.component.inject(this)
    }

    override val dataSource: BaseDataSource<Snippet, SnippetsResponce>
        get() = snippetsDataSourceFactory.snippetsDataSource

    val snippets: LiveData<PagedList<Snippet>> = LivePagedListBuilder<String, Snippet>(snippetsDataSourceFactory, Constants.listPagedConfig).build()

    override fun onCleared() {
        super.onCleared()
        snippetsDataSourceFactory.snippetsDataSource.invalidate()
    }

    fun navigateToCode(url: String) {
        router.navigateTo(Screens.SNIPPETS_CODE_SCREEN, url)
    }
}