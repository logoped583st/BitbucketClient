package bushuk.stanislau.bitbucketproject.presentation.follow.following


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import bushuk.stanislau.bitbucketproject.adapters.RecyclerFollowAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentFollowersBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment


class FollowingFragment : BaseFollowFragment() {


    override fun provideBaseFollowAdapter(adapter: RecyclerFollowAdapter) {
        viewModel.following.observe(this, Observer(adapter::submitList))
    }

    lateinit var binding: FragmentFollowersBinding

    lateinit var viewModel: FollowingViewModel

    override fun provideBaseFollowFragmentBinding(binding: FragmentFollowersBinding) {
        this.binding = binding

        viewModel = ViewModelProviders.of(this).get(FollowingViewModel::class.java)

        binding.let {
            it.modelFollow = viewModel.loadingModel
            it.setLifecycleOwner(this)
        }
    }

}
