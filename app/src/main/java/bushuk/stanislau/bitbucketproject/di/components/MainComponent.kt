package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.MainActivityViewModel
import bushuk.stanislau.bitbucketproject.datasources.*
import bushuk.stanislau.bitbucketproject.di.modules.auth.AuthLoginModule
import bushuk.stanislau.bitbucketproject.di.modules.codeeditor.CodeEditorModule
import bushuk.stanislau.bitbucketproject.di.modules.followers.FollowersModule
import bushuk.stanislau.bitbucketproject.di.modules.following.FollowingModule
import bushuk.stanislau.bitbucketproject.di.modules.global.*
import bushuk.stanislau.bitbucketproject.di.modules.mainScreen.MainScreenModule
import bushuk.stanislau.bitbucketproject.di.modules.pullrequest.PullRequestScopeModule
import bushuk.stanislau.bitbucketproject.di.modules.repositories.RepositoriesModule
import bushuk.stanislau.bitbucketproject.di.modules.repository.RepositoryModule
import bushuk.stanislau.bitbucketproject.di.modules.snippets.SnippetsModule
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginActivity
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginViewModel
import bushuk.stanislau.bitbucketproject.presentation.auth.model.AuthLoginModel
import bushuk.stanislau.bitbucketproject.presentation.codeeditor.CodeEditorModel
import bushuk.stanislau.bitbucketproject.presentation.codeeditor.CodeEditorViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.FollowDataSource
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.FollowersViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.follow.following.FollowingViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.login.LoginActivity
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenViewModel
import bushuk.stanislau.bitbucketproject.presentation.main.model.MainScreenModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.PullRequestViewModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.model.CommitsDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.model.ReviewersDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesViewModel
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.repository.RepositoryFragment
import bushuk.stanislau.bitbucketproject.presentation.repository.RepositoryViewModel
import bushuk.stanislau.bitbucketproject.presentation.snippets.SnippetsViewModel
import bushuk.stanislau.bitbucketproject.presentation.snippets.models.SnippetsDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.user.UserViewModel
import bushuk.stanislau.bitbucketproject.utils.crypt.CryptApi19
import bushuk.stanislau.bitbucketproject.utils.crypt.CryptApi23
import bushuk.stanislau.bitbucketproject.utils.preferences.SharedPreferencesUtil
import bushuk.stanislau.bitbucketproject.utils.retrofit.AuthorizationInterceptor
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CiceroneModule::class, CryptoModule::class, RetrofitModule::class,
    MainScreenModule::class, ApplicationContextProvider::class, PreferencesModule::class,
    AuthLoginModule::class, RoomModule::class, UserModule::class, RepositoriesModule::class,
    FollowersModule::class, FollowingModule::class, FollowModule::class, SnippetsModule::class,
    RepositoryModule::class, PullRequestModule::class, PullRequestScopeModule::class, CodeEditorModule::class])

interface MainComponent {

    fun inject(loginActivity: LoginActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(mainScreenModel: MainScreenModel)

    fun inject(mainScreenViewModel: MainScreenViewModel)

    fun inject(cryptApi19: CryptApi19)

    fun inject(cryptApi23: CryptApi23)

    fun inject(tokenPreferences: SharedPreferencesUtil)

    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun inject(mainScreenActivity: MainScreenActivity)

    fun inject(authorizationInterceptor: AuthorizationInterceptor)

    fun inject(authLoginModel: AuthLoginModel)

    fun inject(authLoginViewModel: AuthLoginViewModel)

    fun inject(authLoginActivity: AuthLoginActivity)

    fun inject(repositoriesViewModel: RepositoriesViewModel)

    fun inject(userModel: UserModel)

    fun inject(repositoriesDataSourceFactory: RepositoriesDataSourceFactory)

    fun inject(followersViewModel: FollowersViewModel)

    fun inject(followersDataSource: FollowDataSource)

    fun inject(followersDataSourceFactory: FollowersDataSourceFactory)

    fun inject(followingViewModel: FollowingViewModel)

    fun inject(followingDataSourceFactory: FollowingDataSourceFactory)

    fun inject(snippetsDataSourceFactory: SnippetsDataSourceFactory)

    fun inject(snippetsViewModel: SnippetsViewModel)

    fun inject(userViewModel: UserViewModel)

    fun inject(baseFollowViewModel: BaseFollowViewModel)

    fun inject(repositoryViewModel: RepositoryViewModel)

    fun inject(repositoryFragment: RepositoryFragment)

    fun initCodeComponent(): CodeComponent

    fun initPullRequestsComponent(): PullRequestsComponent

    fun initWatchersComponent(): WatchersComponent

    fun inject(pullRequestViewModel: PullRequestViewModel)

    fun inject(commitsDataSourceFactory: CommitsDataSourceFactory)

    fun inject(reviewersDataSourceFactory: ReviewersDataSourceFactory)

    fun inject(repositoriesDataSourceAbstract: RepositoriesDataSourceAbstract)

    fun inject(usersDataSourceAbstract: UsersDataSourceAbstract)

    fun inject(codeDataSourceAbstract: CodeDataSourceAbstract)

    fun inject(pullRequestDataSourceAbstract: PullRequestsDataSourceAbstract)

    fun inject(snippetsDataSourceAbstract: SnippetsDataSourceAbstract)

    fun inject(commitsDataSourceAbstract: CommitsDataSourceAbstract)

    fun inject(reviewersDataSourceAbstract: ReviewersDataSourceAbstract)

    fun inject(codeEditorVIewModel: CodeEditorViewModel)

    fun inject(codeEditorModel: CodeEditorModel)

}