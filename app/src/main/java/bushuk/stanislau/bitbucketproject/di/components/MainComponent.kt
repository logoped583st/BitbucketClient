package bushuk.stanislau.bitbucketproject.di.components

import bushuk.stanislau.bitbucketproject.MainActivity
import bushuk.stanislau.bitbucketproject.di.modules.CiceroneModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CiceroneModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}