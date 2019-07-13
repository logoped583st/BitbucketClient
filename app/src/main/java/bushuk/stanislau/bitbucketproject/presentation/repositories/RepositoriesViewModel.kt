package bushuk.stanislau.bitbucketproject.presentation.repositories

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.adapters.RecyclerAdapter
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.presentation.base.ListLoadingViewModel
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(private val factory: RepositoriesDataSourceFactory,
                                                private val queryModel: RepositoriesQueryModel,
                                                private val router: Router

)
    : ListLoadingViewModel<RepositoriesResponse>() {

    override val state: LiveData<LoadingState.LoadingStateSealed<RepositoriesResponse, CustomExceptions>>
        get() = super.state


    private var repositoryName: String? = null

    private var repositoryAccess: String? = null

    private var repositoryLanguage: String? = null

    val repositories: LiveData<PagedList<Repository>> by lazy { LivePagedListBuilder(factory, Constants.listPagedConfig).build() }


    fun observeSearchView(searchView: SearchView, lifecycleOwner: LifecycleOwner, adapter: RecyclerAdapter<Repository>) {
        compositeDisposable.add(RxSearchView.queryTextChanges(searchView)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .map { it.toString() }
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    repositoryName = it
                    // queryChange(lifecycleOwner, adapter)
                })

    }

    private fun queryChange() {
        queryModel.buildQuery(repositoryName, repositoryAccess, repositoryLanguage)
        factory.dataSource.invalidate()
    }

    fun repositoryLanguageChanged(language: String) {
        repositoryLanguage = language
        queryChange()
    }

    fun repositoryAccessChanged(access: String) {
        repositoryAccess = access
        queryChange()
    }

    fun repositoryNameChanged(name: String) {
        repositoryName = name
        queryChange()
    }

    fun exitFromFragment() {
        //router.exit()
    }

    fun navigateToRepositoryScreen(repository: Repository, username: String) {
    //    repositoryModel.repository.onNext(repository)
        // router.navigateTo(Screens.REPOSITORY_SCREEN, listOf(repository.links.avatar.href, username))
    }

    override fun onCleared() {
        super.onCleared()
        // factory.repositoriesDataSource.invalidate()
    }

    fun navigateToAddRepository(view: View) {
        //router.navigateTo(ScreensNavigator.AddRepositoryScreen())
    }

}