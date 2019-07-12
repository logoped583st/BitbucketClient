package bushuk.stanislau.bitbucketproject.presentation.repositories

import android.view.View
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bushuk.stanislau.bitbucketproject.adapters.RecyclerAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.presentation.base.ListLoadingViewModel
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.jakewharton.rxbinding2.widget.RxAdapterView
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(val factory: RepositoriesDataSourceFactory,
                                                val router: Router)
    : ListLoadingViewModel<RepositoriesResponse>() {

    override val state: LiveData<LoadingState.LoadingStateSealed<RepositoriesResponse, CustomExceptions>>
        get() = super.state

    //@Inject
  //  lateinit var router: Router

   // @Inject
   // lateinit var repositoryModel: RepositoryModel

    private var repositoryName: String? = null

    private var repositoryAccess: String? = null

    private var repositoryLanguage: String? = null

    var repositories: LiveData<PagedList<Repository>> = LivePagedListBuilder(factory, Constants.listPagedConfig).build()


    fun observeSearchView(searchView: SearchView, lifecycleOwner: LifecycleOwner, adapter: RecyclerAdapter<Repository>) {
        compositeDisposable.add(RxSearchView.queryTextChanges(searchView)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .map { it.toString() }
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    repositoryName = it
                    queryChange(lifecycleOwner, adapter)
                })

    }


    fun observeLanguageChangeSpinner(spinner: AppCompatSpinner, lifecycleOwner: LifecycleOwner, adapter: RecyclerAdapter<Repository>) {
        compositeDisposable.add(RxAdapterView.itemSelections(spinner)
                .skipInitialValue()
                .map { (spinner.adapter as SpinnerAdapter).getItem(it) }
                .subscribe({
                    repositoryLanguage = it
                    queryChange(lifecycleOwner, adapter)
                }, {
                    Timber.e(it.message)
                }))
    }

    fun observeAccessChangeSpinner(spinner: AppCompatSpinner, lifecycleOwner: LifecycleOwner, adapter: RecyclerAdapter<Repository>) {
        compositeDisposable.add(RxAdapterView.itemSelections(spinner)
                .skipInitialValue()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { (spinner.adapter as SpinnerAdapter).getItem(it) }
                .subscribe({
                    repositoryAccess = it
                    queryChange(lifecycleOwner, adapter)
                }, {
                    Timber.e(it.message)
                }))
    }


    private fun queryChange(lifecycleOwner: LifecycleOwner, adapter: RecyclerAdapter<Repository>) {
        repositories.removeObservers(lifecycleOwner)
        //factory.repositoriesDataSource.url = UrlBuilder.buildQuery(repositoryName, repositoryAccess, repositoryLanguage)
        repositories = LivePagedListBuilder(factory, Constants.listPagedConfig).build()
        repositories.observe(lifecycleOwner, Observer(adapter::submitList))
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