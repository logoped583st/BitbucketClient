package bushuk.stanislau.bitbucketproject.presentation.followers


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerFollowersAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentFollowersBinding
import kotlinx.android.synthetic.main.fragment_followers.*

class FollowersFragment : Fragment() {

    lateinit var viewModel: FollowersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentFollowersBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_followers, container, false)
        viewModel = ViewModelProviders.of(this).get(FollowersViewModel::class.java)

        binding.let {
            it.viewModelFollowers = viewModel
            it.setLifecycleOwner(this)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followers_screen_recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = RecyclerFollowersAdapter()
        followers_screen_recycler.adapter = adapter
        viewModel.followers.observe(this, Observer(adapter::submitList))
    }
}
