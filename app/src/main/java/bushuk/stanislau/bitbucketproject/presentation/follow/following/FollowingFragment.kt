package bushuk.stanislau.bitbucketproject.presentation.follow.following


import android.arch.lifecycle.ViewModelProviders
import bushuk.stanislau.bitbucketproject.databinding.FragmentFollowersBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment


class FollowingFragment : BaseFollowFragment() {


    override fun provideBaseFollowFragmentBinding(binding: FragmentFollowersBinding) {
        baseFollowViewModel = ViewModelProviders.of(this).get(FollowingViewModel::class.java)

        binding.let {
            it.baseFollowrs = (baseFollowViewModel as FollowingViewModel)._factory.followingDataSource
            it.setLifecycleOwner(this)
        }
    }

}
