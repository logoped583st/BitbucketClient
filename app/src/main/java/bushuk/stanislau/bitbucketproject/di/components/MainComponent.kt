package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.MainActivityViewModel
import bushuk.stanislau.bitbucketproject.di.modules.auth.AuthLoginModule
import bushuk.stanislau.bitbucketproject.di.modules.global.*
import bushuk.stanislau.bitbucketproject.di.modules.mainScreen.MainScreenModule
import bushuk.stanislau.bitbucketproject.di.modules.repositories.RepositoriesModule
import bushuk.stanislau.bitbucketproject.globalModels.UserModel
import bushuk.stanislau.bitbucketproject.presentation.baseAuth.AuthLoginActivity
import bushuk.stanislau.bitbucketproject.presentation.baseAuth.model.AuthLoginModel
import bushuk.stanislau.bitbucketproject.presentation.baseAuth.viewModel.AuthLoginViewModel
import bushuk.stanislau.bitbucketproject.presentation.login.LoginActivity
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import bushuk.stanislau.bitbucketproject.presentation.main.model.MainScreenModel
import bushuk.stanislau.bitbucketproject.presentation.main.viewModel.MainScreenViewModel
import bushuk.stanislau.bitbucketproject.presentation.repositories.model.RepositoriesModel
import bushuk.stanislau.bitbucketproject.presentation.repositories.viewModel.RepositoriesViewModel
import bushuk.stanislau.bitbucketproject.utils.cryptUtils.CryptApi19
import bushuk.stanislau.bitbucketproject.utils.cryptUtils.CryptApi23
import bushuk.stanislau.bitbucketproject.utils.retrofitUtils.AuthorizationInterceptor
import bushuk.stanislau.bitbucketproject.utils.sharedPreferencesUtils.SharedPreferencesUtil
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CiceroneModule::class, CryptoModule::class, RetrofitModule::class,
    MainScreenModule::class, ApplicationContextProvider::class, PreferencesModule::class,
    AuthLoginModule::class, RoomModule::class, RepositoriesModule::class, UserModule::class])
interface MainComponent {

    fun inject(loginActivity: LoginActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(mainScreenModel: MainScreenModel)

    fun inject(mainScreenViewModel: MainScreenViewModel)

    fun inject(cryptApi19: CryptApi19)

    fun inject(cryptApi23: CryptApi23)

    fun inject(tokenPreferences: SharedPreferencesUtil)

    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun inject(mainScreenActivity: MainScreenActivity)

    fun inject(authorizationInterceptor: AuthorizationInterceptor)

    fun inject(authLoginModel: AuthLoginModel)

    fun inject(authLoginViewModel: AuthLoginViewModel)

    fun inject(authLoginActivity: AuthLoginActivity)

    fun inject(repositoriesModel: RepositoriesModel)

    fun inject(repositoriesViewModel: RepositoriesViewModel)

    fun inject(userModel: UserModel)
}