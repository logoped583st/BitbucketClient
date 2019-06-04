package bushuk.stanislau.bitbucketproject.presentation.follow

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.DataSource
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerFollowAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentFollowersBinding
import bushuk.stanislau.bitbucketproject.room.user.User
import kotlinx.android.synthetic.main.fragment_followers.*

abstract class BaseFollowFragment<ViewModel : BaseFollowViewModel<DataSource.Factory<String, User>>> : Fragment(), ClickFollow {

    abstract var viewModelClass: Class<ViewModel>

    lateinit var viewModel: ViewModel

    lateinit var binding: FragmentFollowersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.fragment_followers, container, false)

        viewModel = ViewModelProviders.of(this).get(viewModelClass)

        binding.let {
            it.setLifecycleOwner(this)
            it.loading = (viewModel).liveLoadingModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followers_screen_recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = RecyclerFollowAdapter<ViewModel>()
        followers_screen_recycler.adapter = adapter
        adapter.setListener(this)

        viewModel.followers.observe(this, Observer(adapter::submitList))
    }
}