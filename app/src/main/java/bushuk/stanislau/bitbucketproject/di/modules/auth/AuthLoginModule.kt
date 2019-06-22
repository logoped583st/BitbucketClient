package bushuk.stanislau.bitbucketproject.di.modules.auth

import androidx.fragment.app.Fragment
import bushuk.stanislau.bitbucketproject.di.scopes.SimpleFragmentScope
import bushuk.stanislau.bitbucketproject.presentation.auth.AuthLoginFragment
import bushuk.stanislau.bitbucketproject.presentation.auth.model.AuthLoginModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthLoginModule {

    @Provides
    @Singleton
    fun provideAuthLoginModel():AuthLoginModel = AuthLoginModel()
}


@Module
abstract class AuthLoginFragmentModule {
    @Binds
    @SimpleFragmentScope
    abstract fun fragment(fragment: AuthLoginFragment): Fragment
}