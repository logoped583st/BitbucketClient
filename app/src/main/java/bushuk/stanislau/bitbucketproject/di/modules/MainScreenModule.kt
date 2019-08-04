package bushuk.stanislau.bitbucketproject.di.modules

import androidx.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.di.ViewModelKey
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.multibindings.IntoMap

@Module(includes = [AndroidInjectionModule::class])
abstract class MainScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainScreenViewModel): ViewModel

}