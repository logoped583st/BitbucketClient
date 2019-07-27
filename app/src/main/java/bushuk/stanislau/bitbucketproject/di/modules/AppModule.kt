package bushuk.stanislau.bitbucketproject.di.modules

import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.di.modules.auth.AuthLoginModule
import bushuk.stanislau.bitbucketproject.di.modules.drawer.MainScreenModule
import bushuk.stanislau.bitbucketproject.di.modules.drawer.RepositoriesModule
import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.di.scopes.SimpleFragmentScope
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginFragment
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthWebFragment
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenFragment
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesFragment
import bushuk.stanislau.bitbucketproject.presentation.userslist.followers.FollowersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @DrawerScope
    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity

    @SimpleFragmentScope
    @ContributesAndroidInjector(modules = [AuthLoginModule::class])
    abstract fun AuthLoginFragmentInjector(): AuthLoginFragment

    @SimpleFragmentScope
    @ContributesAndroidInjector
    abstract fun AuthLoginBrowserFragmentInjector(): AuthWebFragment

    @DrawerScope
    @ContributesAndroidInjector(modules = [MainScreenModule::class])
    abstract fun MainScreenFragmentInjector(): MainScreenFragment

    @DrawerScope
    @ContributesAndroidInjector(modules = [RepositoriesModule::class])
    abstract fun RepositoriesFragmentInjector(): RepositoriesFragment

    @DrawerScope
    @ContributesAndroidInjector
    abstract fun FollowwersFragmentInjector(): FollowersFragment


}