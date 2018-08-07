package bushuk.stanislau.bitbucketproject.presentation.follow.followers


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import bushuk.stanislau.bitbucketproject.adapters.RecyclerFollowAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentFollowersBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment
import bushuk.stanislau.bitbucketproject.room.user.User

class FollowersFragment : BaseFollowFragment() {

    override fun onClickItem(view: View, data: Any) {
        viewModel.navigateToUserScreen((data as User))
    }

    override fun provideBaseFollowAdapter(adapter: RecyclerFollowAdapter) {
        viewModel.followers.observe(this, Observer(adapter::submitList))
    }

    lateinit var viewModel: FollowersViewModel

    override fun provideBaseFollowFragmentBinding(binding: FragmentFollowersBinding) {
        viewModel = ViewModelProviders.of(this).get(FollowersViewModel::class.java)

        binding.let {
            it.baseFollowrs = viewModel._factory.followersDataSource
            it.setLifecycleOwner(this)
        }
    }


}
