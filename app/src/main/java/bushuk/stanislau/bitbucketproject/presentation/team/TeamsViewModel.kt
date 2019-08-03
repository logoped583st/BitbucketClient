package bushuk.stanislau.bitbucketproject.presentation.team

import android.view.View
import bushuk.stanislau.bitbucketproject.di.CiceroneFactory
import bushuk.stanislau.bitbucketproject.presentation.base.BaseListLoadingViewModel
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import bushuk.stanislau.bitbucketproject.room.team.Team
import bushuk.stanislau.bitbucketproject.room.team.TeamResponse
import javax.inject.Inject

class TeamsViewModel @Inject constructor(factory: TeamsDataSourceFactory,
                                         factoryTest: RepositoriesDataSourceFactory,
                                         private val routerFactory: CiceroneFactory

) : BaseListLoadingViewModel<Team, TeamResponse>(factory) {


    enum class TeamRole(role: String) {
        ADMIN("admin"),
        CONTRIBUTER("contributer"),
        MEMBER("member")
    }

    var role: TeamRole = TeamRole.MEMBER


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