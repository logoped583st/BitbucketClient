package bushuk.stanislau.bitbucketproject.presentation.follow.following


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import bushuk.stanislau.bitbucketproject.adapters.RecyclerFollowAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentFollowersBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment
import bushuk.stanislau.bitbucketproject.room.user.User


class FollowingFragment : BaseFollowFragment() {

    override fun onClickItem(view: View, data: Any) {
        viewModel.navigateToUserScreen((data as User))
    }

    override fun provideBaseFollowAdapter(adapter: RecyclerFollowAdapter) {
        viewModel.followers.observe(this, Observer(adapter::submitList))
    }

    lateinit var viewModel: FollowingViewModel

    override fun provideBaseFollowFragmentBinding(binding: FragmentFollowersBinding) {


        viewModel = ViewModelProviders.of(this).get(FollowingViewModel::class.java)

        binding.let {
            it.baseFollowrs = viewModel._factory.followingDataSource
            it.setLifecycleOwner(this)
        }
    }

}
