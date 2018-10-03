package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.LoadingViewModel
import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.MainActivityViewModel
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
import bushuk.stanislau.bitbucketproject.presentation.code.model.CodeDataSource
import bushuk.stanislau.bitbucketproject.presentation.codeeditor.CodeEditorModel
import bushuk.stanislau.bitbucketproject.presentation.codeeditor.CodeEditorViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.FollowersViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSource
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.follow.following.FollowingViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSource
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.login.LoginActivity
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenViewModel
import bushuk.stanislau.bitbucketproject.presentation.main.model.MainScreenModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.ContainerPullRequestViewModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.AlertCodeDialogViewModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.PullRequestCommentsViewModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model.PullRequestCommentsDataSource
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments.model.PullRequestCommentsDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.PullRequestViewModel
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model.CommitsDataSource
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model.CommitsDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model.ReviewersDataSource
import bushuk.stanislau.bitbucketproject.presentation.pullrequest.info.model.ReviewersDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.pullrequests.model.PullRequestsDataSource
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesViewModel
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSource
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.repository.RepositoryViewModel
import bushuk.stanislau.bitbucketproject.presentation.snippets.SnippetsCodeViewModel
import bushuk.stanislau.bitbucketproject.presentation.snippets.SnippetsViewModel
import bushuk.stanislau.bitbucketproject.presentation.snippets.models.SnippetsDataSource
import bushuk.stanislau.bitbucketproject.presentation.snippets.models.SnippetsDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.user.UserViewModel
import bushuk.stanislau.bitbucketproject.presentation.watchers.model.WatchersDataSource
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
    FollowersModule::class, FollowingModule::class, SnippetsModule::class,
    RepositoryModule::class, PullRequestModule::class, PullRequestScopeModule::class, CodeEditorModule::class, LoadingModule::class])

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

    fun inject(followersDataSourceFactory: FollowersDataSourceFactory)

    fun inject(followingViewModel: FollowingViewModel)

    fun inject(followingDataSourceFactory: FollowingDataSourceFactory)

    fun inject(snippetsDataSourceFactory: SnippetsDataSourceFactory)

    fun inject(snippetsViewModel: SnippetsViewModel)

    fun inject(userViewModel: UserViewModel)

    fun inject(baseFollowViewModel: BaseFollowViewModel)

    fun inject(repositoryViewModel: RepositoryViewModel)

    fun initCodeComponent(): CodeComponent

    fun initPullRequestsComponent(): PullRequestsComponent

    fun initWatchersComponent(): WatchersComponent

    fun inject(pullRequestViewModel: PullRequestViewModel)

    fun inject(commitsDataSourceFactory: CommitsDataSourceFactory)

    fun inject(reviewersDataSourceFactory: ReviewersDataSourceFactory)

    fun inject(codeEditorVIewModel: CodeEditorViewModel)

    fun inject(codeEditorModel: CodeEditorModel)

    fun inject(containerPullRequestViewModel: ContainerPullRequestViewModel)

    fun inject(pullRequestCommentsDataSourceFactory: PullRequestCommentsDataSourceFactory)

    fun inject(pullRequestCommentsViewModel: PullRequestCommentsViewModel)

    fun inject(alertCodeDialogViewModel: AlertCodeDialogViewModel)

    fun inject(snippetsCodeViewModel: SnippetsCodeViewModel)

    fun inject(codeDataSource: CodeDataSource)

    fun inject(snippetsDataSource: SnippetsDataSource)

    fun inject(repositoriesDataSource: RepositoriesDataSource)

    fun inject(commitsDataSource: CommitsDataSource)

    fun inject(pullRequestDataSource: PullRequestsDataSource)

    fun inject(followersDataSource: FollowersDataSource)

    fun inject(watchersDataSource: WatchersDataSource)

    fun inject(reviewersDataSource: ReviewersDataSource)

    fun inject(pullRequestCommentsDataSource: PullRequestCommentsDataSource)

    fun inject(followingDataSource: FollowingDataSource)

    fun inject(loadingViewModel: LoadingViewModel<Any,Any>)
}