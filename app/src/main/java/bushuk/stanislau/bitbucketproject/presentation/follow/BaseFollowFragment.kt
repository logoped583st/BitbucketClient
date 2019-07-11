package bushuk.stanislau.bitbucketproject.presentation.follow

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.recyclerview.widget.LinearLayoutManager
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentFollowersBinding
import bushuk.stanislau.bitbucketproject.presentation.base.BaseBindingFragment
import bushuk.stanislau.bitbucketproject.room.user.User
import kotlinx.android.synthetic.main.fragment_followers.*

abstract class BaseFollowFragment<ViewModel : BaseFollowViewModel<DataSource.Factory<String, User>>> :
        BaseBindingFragment<ViewModel, FragmentFollowersBinding>(), ClickFollow<User> {

    override fun applyBinding() {
        binding.loading = viewModel.liveLoadingModel
    }

    override val containerId: Int = R.layout.fragment_followers

    override val scope: ViewModelScope = ViewModelScope.ACTIVITY

    abstract val clickFollow: ClickFollow<User>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followers_screen_recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = RecyclerAdapter(clickFollow)
        followers_screen_recycler.adapter = adapter

        viewModel.followers.observe(this, Observer(adapter::submitList))
    }
}