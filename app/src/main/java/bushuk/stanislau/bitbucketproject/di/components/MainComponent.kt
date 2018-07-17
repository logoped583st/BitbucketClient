package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.di.modules.*
import bushuk.stanislau.bitbucketproject.presentation.login.LoginActivity
import bushuk.stanislau.bitbucketproject.presentation.main.model.MainScreenModel
import bushuk.stanislau.bitbucketproject.presentation.main.viewModel.MainScreenViewModel
import bushuk.stanislau.bitbucketproject.utils.TokenUtils.TokenPreferencesApi19
import bushuk.stanislau.bitbucketproject.utils.TokenUtils.TokenPreferencesApi23
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CiceroneModule::class, TokenPreferencesModule::class, RetrofitModule::class,
    MainScreenModule::class, ApplicationContextProvider::class])
interface MainComponent {

    fun inject(loginActivity: LoginActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(mainScreenModel: MainScreenModel)

    fun inject(mainScreenViewModel: MainScreenViewModel)

    fun inject(tokenPreferencesApi23: TokenPreferencesApi23)

    fun inject(tokenPreferencesApi19: TokenPreferencesApi19)

}