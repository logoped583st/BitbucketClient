package bushuk.stanislau.bitbucketproject.di.modules

import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.di.scopes.ActivityScope
import bushuk.stanislau.bitbucketproject.di.scopes.SimpleFragmentScope
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginFragment
import bushuk.stanislau.bitbucketproject.presentation.auth.LoginWebFragment
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenFragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class AppModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity

    @SimpleFragmentScope
    @ContributesAndroidInjector
    abstract fun AuthLoginFragmentInjector(): AuthLoginFragment

    @SimpleFragmentScope
    @ContributesAndroidInjector
    abstract fun AuthLoginBrowserFragmentInjector(): LoginWebFragment

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun MainScreenFragmentInjector(): MainScreenFragment


}