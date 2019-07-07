package bushuk.stanislau.bitbucketproject.di.components


import bushuk.stanislau.bitbucketproject.di.modules.codeeditor.CodeEditorModule
import bushuk.stanislau.bitbucketproject.di.modules.followers.FollowersModule
import bushuk.stanislau.bitbucketproject.di.modules.following.FollowingModule
import bushuk.stanislau.bitbucketproject.di.modules.global.*
import bushuk.stanislau.bitbucketproject.di.modules.pullrequest.PullRequestScopeModule
import bushuk.stanislau.bitbucketproject.di.modules.repositories.RepositoriesModule
import bushuk.stanislau.bitbucketproject.di.modules.repository.RepositoryModule
import bushuk.stanislau.bitbucketproject.di.modules.snippets.SnippetsModule
import bushuk.stanislau.bitbucketproject.global.UserModel
import bushuk.stanislau.bitbucketproject.presentation.addrepository.AddRepositoryViewModel
import bushuk.stanislau.bitbucketproject.presentation.code.model.CodeDataSource
import bushuk.stanislau.bitbucketproject.presentation.codeeditor.CodeEditorModel
import bushuk.stanislau.bitbucketproject.presentation.codeeditor.CodeEditorViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.FollowersViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSource
import bushuk.stanislau.bitbucketproject.presentation.follow.followers.models.FollowersDataSourceFactory
import bushuk.stanislau.bitbucketproject.presentation.follow.following.FollowingViewModel
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSource
import bushuk.stanislau.bitbucketproject.presentation.follow.following.models.FollowingDataSourceFactory
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
import bushuk.stanislau.bitbucketproject.presentation.watchers.WatchersViewModel
import bushuk.stanislau.bitbucketproject.presentation.watchers.model.WatchersDataSource
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [

    RoomModule::class, RepositoriesModule::class,
    FollowersModule::class, FollowingModule::class, SnippetsModule::class,
    RepositoryModule::class, PullRequestModule::class, PullRequestScopeModule::class, CodeEditorModule::class])

interface MainComponent {


//    fun inject(repositoriesViewModel: RepositoriesViewModel)
//
//    fun inject(repositoriesDataSourceFactory: RepositoriesDataSourceFactory)
//
//    fun inject(followersViewModel: FollowersViewModel)
//
//    fun inject(followersDataSourceFactory: FollowersDataSourceFactory)
//
//    fun inject(followingViewModel: FollowingViewModel)
//
//    fun inject(followingDataSourceFactory: FollowingDataSourceFactory)
//
//    fun inject(snippetsDataSourceFactory: SnippetsDataSourceFactory)
//
//    fun inject(snippetsViewModel: SnippetsViewModel)
//
//    fun inject(userViewModel: UserViewModel)
//
//    fun inject(repositoryViewModel: RepositoryViewModel)
//
//    fun initCodeComponent(): CodeComponent
//
//    fun initPullRequestsComponent(): PullRequestsComponent
//
//    fun initWatchersComponent(): WatchersComponent
//
//    fun inject(pullRequestViewModel: PullRequestViewModel)
//
//    fun inject(commitsDataSourceFactory: CommitsDataSourceFactory)
//
//    fun inject(reviewersDataSourceFactory: ReviewersDataSourceFactory)
//
//    fun inject(codeEditorVIewModel: CodeEditorViewModel)
//
//    fun inject(codeEditorModel: CodeEditorModel)
//
//    fun inject(containerPullRequestViewModel: ContainerPullRequestViewModel)
//
//    fun inject(pullRequestCommentsDataSourceFactory: PullRequestCommentsDataSourceFactory)
//
//    fun inject(pullRequestCommentsViewModel: PullRequestCommentsViewModel)
//
//    fun inject(alertCodeDialogViewModel: AlertCodeDialogViewModel)
//
//    fun inject(snippetsCodeViewModel: SnippetsCodeViewModel)
//
//    fun inject(codeDataSource: CodeDataSource)
//
//    fun inject(snippetsDataSource: SnippetsDataSource)
//
//    fun inject(repositoriesDataSource: RepositoriesDataSource)
//
//    fun inject(commitsDataSource: CommitsDataSource)
//
//    fun inject(pullRequestDataSource: PullRequestsDataSource)
//
//    fun inject(followersDataSource: FollowersDataSource)
//
//    fun inject(watchersDataSource: WatchersDataSource)
//
//    fun inject(reviewersDataSource: ReviewersDataSource)
//
//    fun inject(pullRequestCommentsDataSource: PullRequestCommentsDataSource)
//
//    fun inject(followingDataSource: FollowingDataSource)
//
//    fun inject(watchersViewModel: WatchersViewModel)
//
//    fun inject(addRepositoryViewModel: AddRepositoryViewModel)
}