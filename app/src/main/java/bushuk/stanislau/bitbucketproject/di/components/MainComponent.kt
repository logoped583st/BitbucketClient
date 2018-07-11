package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.di.modules.CiceroneModule
import bushuk.stanislau.bitbucketproject.di.modules.TokenPreferencesModule
import bushuk.stanislau.bitbucketproject.presentation.loginPresentation.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CiceroneModule::class,TokenPreferencesModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(loginActivity: LoginActivity)
}