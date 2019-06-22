package bushuk.stanislau.bitbucketproject.di.modules

import androidx.appcompat.app.AppCompatActivity
import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.di.modules.auth.AuthLoginFragmentModule
import bushuk.stanislau.bitbucketproject.di.scopes.ActivityScope
import bushuk.stanislau.bitbucketproject.di.scopes.SimpleFragmentScope
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @SimpleFragmentScope
    @ContributesAndroidInjector(modules = [AuthLoginFragmentModule::class])
    abstract fun AuthLoginFragmentInjector(): AuthLoginFragment


    @Binds
    @ActivityScope
    abstract fun activity(mainActivity: MainActivity): AppCompatActivity
}