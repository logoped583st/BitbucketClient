package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.di.modules.CiceroneModule
import bushuk.stanislau.bitbucketproject.di.modules.MainScreenModule
import bushuk.stanislau.bitbucketproject.di.modules.RetrofitModule
import bushuk.stanislau.bitbucketproject.di.modules.TokenPreferencesModule
import bushuk.stanislau.bitbucketproject.presentation.login.LoginActivity
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import bushuk.stanislau.bitbucketproject.presentation.main.model.MainScreenModel
import bushuk.stanislau.bitbucketproject.presentation.main.viewModel.MainScreenViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CiceroneModule::class, TokenPreferencesModule::class, RetrofitModule::class,
    MainScreenModule::class])
interface MainComponent {

    fun inject(loginActivity: LoginActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(mainScreenModel: MainScreenModel)

    fun inject(mainScreenViewModel: MainScreenViewModel)

}