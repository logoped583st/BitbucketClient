package bushuk.stanislau.bitbucketproject.presentation.watchers


import android.arch.lifecycle.ViewModelProviders
import bushuk.stanislau.bitbucketproject.databinding.FragmentFollowersBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment


class WatchersFragment : BaseFollowFragment() {


    override fun provideBaseFollowFragmentBinding(binding: FragmentFollowersBinding) {
        baseFollowViewModel = ViewModelProviders.of(this).get(WatchersViewModel::class.java)

        binding.let {
            it.baseFollowrs = (baseFollowViewModel as WatchersViewModel).watchersDataSourceFactory.watchersDataSource
            it.setLifecycleOwner(this)
        }
    }

}
