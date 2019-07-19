package bushuk.stanislau.bitbucketproject.presentation.repositories

import android.view.View
import androidx.lifecycle.LiveData
import bushuk.stanislau.bitbucketproject.di.CiceroneFactory
import bushuk.stanislau.bitbucketproject.global.LoadingStateSealed
import bushuk.stanislau.bitbucketproject.presentation.base.ListLoadingViewModel
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(factory: RepositoriesDataSourceFactory,
                                                private val queryModel: RepositoriesQueryModel,
                                                private val routerFactory: CiceroneFactory

) : ListLoadingViewModel<Repository, RepositoriesResponse>(factory) {

    override val state: LiveData<LoadingStateSealed<RepositoriesResponse, CustomExceptions>> = factory.state

    var repositoryName: String? = null
        set(value) {
            field = value
            queryChange()
        }

    var repositoryAccess: String? = null
        set(value) {
            field = value
            queryChange()
        }

    var repositoryLanguage: String? = null
        set(value) {
            field = value
            queryChange()
        }



//    fun observeSearchView(searchView: SearchView, lifecycleOwner: LifecycleOwner, adapter: RecyclerAdapter<Repository>) {
//        compositeDisposable.add(RxSearchView.queryTextChanges(searchView)
//                .skipInitialValue()
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .map { it.toString() }
//                .debounce(1000, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    repositoryName = it
//                    // queryChange(lifecycleOwner, adapter)
//                })
//    }

    private fun queryChange() {
        clearPaging()
        queryModel.buildQuery(repositoryName, repositoryAccess, repositoryLanguage)
    }

    fun exitFromFragment() {
        //router.exit()
    }

    fun navigateToRepositoryScreen(repository: Repository, username: String) {
    //    repositoryModel.repository.onNext(repository)
        // router.navigateTo(Screens.REPOSITORY_SCREEN, listOf(repository.links.avatar.href, username))
    }


    fun navigateToAddRepository(view: View) {
        //router.navigateTo(ScreensNavigator.AddRepositoryScreen())
    }

}