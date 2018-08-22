package bushuk.stanislau.bitbucketproject.presentation.follow

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerFollowAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentFollowersBinding
import bushuk.stanislau.bitbucketproject.room.user.User
import kotlinx.android.synthetic.main.fragment_followers.*

abstract class BaseFollowFragment : Fragment(), ClickFollow {


    lateinit var baseFollowViewModel: BaseFollowViewModel

    override fun onClickItem(view: View, data: Any) {
        baseFollowViewModel.navigateToUserScreen(data as User)
    }

    lateinit var binding: FragmentFollowersBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.fragment_followers, container, false)

        provideBaseFollowFragmentBinding(binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followers_screen_recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = RecyclerFollowAdapter()
        followers_screen_recycler.adapter = adapter
        adapter.setListener(this)

        baseFollowViewModel.followers.observe(this, Observer(adapter::submitList))
    }


    abstract fun provideBaseFollowFragmentBinding(binding: FragmentFollowersBinding)

}