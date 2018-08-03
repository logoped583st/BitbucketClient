package bushuk.stanislau.bitbucketproject.presentation.follow.followers


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import bushuk.stanislau.bitbucketproject.adapters.RecyclerFollowAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentFollowersBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.BaseFollowFragment

class FollowersFragment : BaseFollowFragment() {

    lateinit var adapter: RecyclerFollowAdapter

    override fun provideBaseFollowAdapter(adapter: RecyclerFollowAdapter) {
        this.adapter = adapter
        viewModel.followers.observe(this, Observer(adapter::submitList))
    }

    lateinit var binding: FragmentFollowersBinding

    lateinit var viewModel: FollowersViewModel

    override fun provideBaseFollowFragmentBinding(binding: FragmentFollowersBinding) {
        this.binding = binding

        viewModel = ViewModelProviders.of(this).get(FollowersViewModel::class.java)

        binding.let {
            it.setLifecycleOwner(this)
            it.modelFollow = viewModel.loadingModel
        }
    }


}
