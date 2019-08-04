package bushuk.stanislau.bitbucketproject.presentation.repositories

import android.view.View
import bushuk.stanislau.bitbucketproject.di.CiceroneFactory
import bushuk.stanislau.bitbucketproject.presentation.base.BaseListLoadingViewModel
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(factory: RepositoriesDataSourceFactory,
                                                private val queryModel: IRepositoriesQueryModel,
                                                private val routerFactory: CiceroneFactory

) : BaseListLoadingViewModel<Repository, RepositoriesResponse>(factory) {

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
        queryModel.buildQuery(repositoryName, repositoryAccess, repositoryLanguage)
        clearPaging()
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