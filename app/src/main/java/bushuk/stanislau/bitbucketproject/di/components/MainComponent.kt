package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.di.modules.*
import bushuk.stanislau.bitbucketproject.presentation.login.LoginActivity
import bushuk.stanislau.bitbucketproject.presentation.main.model.MainScreenModel
import bushuk.stanislau.bitbucketproject.presentation.main.viewModel.MainScreenViewModel
import bushuk.stanislau.bitbucketproject.utils.CryptUtils.CryptApi19
import bushuk.stanislau.bitbucketproject.utils.CryptUtils.CryptApi23
import bushuk.stanislau.bitbucketproject.utils.TokenUtils.TokenPreferences
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CiceroneModule::class, CryptoModule::class, RetrofitModule::class,
    MainScreenModule::class, ApplicationContextProvider::class, TokenPreferencesModule::class])
interface MainComponent {

    fun inject(loginActivity: LoginActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(mainScreenModel: MainScreenModel)

    fun inject(mainScreenViewModel: MainScreenViewModel)

    fun inject(cryptApi19: CryptApi19)

    fun inject(cryptApi23: CryptApi23)

    fun inject(tokenPreferences: TokenPreferences)

}