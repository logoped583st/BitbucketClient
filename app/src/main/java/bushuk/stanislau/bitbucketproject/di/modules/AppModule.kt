package bushuk.stanislau.bitbucketproject.di.modules

import androidx.appcompat.app.AppCompatActivity
import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.di.modules.drawer.RepositoriesModule
import bushuk.stanislau.bitbucketproject.di.modules.drawer.TeamsModule
import bushuk.stanislau.bitbucketproject.di.scopes.ActivityScope
import bushuk.stanislau.bitbucketproject.di.scopes.DrawerScope
import bushuk.stanislau.bitbucketproject.di.scopes.SimpleFragmentScope
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginFragment
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthWebFragment
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenFragment
import bushuk.stanislau.bitbucketproject.presentation.repositories.RepositoriesFragment
import bushuk.stanislau.bitbucketproject.presentation.team.TeamsFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class, ActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @Module
    abstract class MainActivityModule {

        @Binds
        @ActivityScope
        abstract fun activity(mainActivity: MainActivity): AppCompatActivity

        @SimpleFragmentScope
        @ContributesAndroidInjector(modules = [AuthLoginModule::class])
        abstract fun AuthLoginFragmentInjector(): AuthLoginFragment

        @SimpleFragmentScope
        @ContributesAndroidInjector(modules = [AuthLoginModule::class])
        abstract fun AuthWebLoginFragmentInjector(): AuthWebFragment

        @DrawerScope
        @ContributesAndroidInjector(modules = [MainScreenModule::class])
        abstract fun MainScreenFragmentInjector(): MainScreenFragment

        @DrawerScope
        @ContributesAndroidInjector(modules = [RepositoriesModule::class])
        abstract fun RepositoriesFragmentInjector(): RepositoriesFragment

        @DrawerScope
        @ContributesAndroidInjector(modules = [TeamsModule::class])
        abstract fun TeamsFragmentInjector(): TeamsFragment
    }
}
