package bushuk.stanislau.bitbucketproject.presentation.watchers


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.view.View

import bushuk.stanislau.bitbucketproject.adapters.RecyclerFollowAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentFollowersBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment
import bushuk.stanislau.bitbucketproject.room.user.User


class WatchersFragment : BaseFollowFragment() {

    lateinit var viewModel: WatchersViewModel

    override fun provideBaseFollowFragmentBinding(binding: FragmentFollowersBinding) {
        viewModel = ViewModelProviders.of(this).get(WatchersViewModel::class.java)

        binding.let {
            it.baseFollowrs = viewModel.watchersDataSourceFactory.followDataSource
            it.setLifecycleOwner(this)
        }
    }

    override fun provideBaseFollowAdapter(adapter: RecyclerFollowAdapter) {
        viewModel.watchers.observe(this, Observer(adapter::submitList))
    }

    override fun onClickItem(view: View, data: Any) {
        viewModel.navigateToUserScreen(data as User)
    }


}
