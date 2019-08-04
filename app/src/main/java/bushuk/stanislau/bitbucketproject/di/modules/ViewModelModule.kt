package bushuk.stanislau.bitbucketproject.di.modules

import androidx.lifecycle.ViewModelProvider
import bushuk.stanislau.bitbucketproject.di.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule

@Module(includes = [AndroidInjectionModule::class])
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
