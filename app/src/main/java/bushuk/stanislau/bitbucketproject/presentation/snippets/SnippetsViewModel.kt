package bushuk.stanislau.bitbucketproject.presentation.snippets

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.presentation.snippets.models.SnippetsDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet
import javax.inject.Inject

class SnippetsViewModel : ViewModel() {

    @Inject
    lateinit var snippetsDataSourceFactory: SnippetsDataSourceFactory


    init {
        App.component.inject(this)
    }

    val snippets: LiveData<PagedList<Snippet>> = LivePagedListBuilder<String, Snippet>(snippetsDataSourceFactory, Constants.listPagedConfig).build()
}