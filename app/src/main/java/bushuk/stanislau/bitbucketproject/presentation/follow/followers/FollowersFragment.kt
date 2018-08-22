package bushuk.stanislau.bitbucketproject.presentation.follow.followers


import android.arch.lifecycle.ViewModelProviders
import bushuk.stanislau.bitbucketproject.databinding.FragmentFollowersBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment

class FollowersFragment : BaseFollowFragment() {


    override fun provideBaseFollowFragmentBinding(binding: FragmentFollowersBinding) {
        baseFollowViewModel = ViewModelProviders.of(this).get(FollowersViewModel::class.java)

        binding.let {
            it.baseFollowrs = (baseFollowViewModel as FollowersViewModel)._factory.followersDataSource
            it.setLifecycleOwner(this)
        }
    }


}
