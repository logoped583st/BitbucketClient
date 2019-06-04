package bushuk.stanislau.bitbucketproject.presentation.pullrequest

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.FragmentContainerPullRequestBinding
import kotlinx.android.synthetic.main.fragment_container_pull_request.view.*


class ContainerPullRequestFragment : Fragment() {

    lateinit var binding: FragmentContainerPullRequestBinding
    lateinit var viewModel: ContainerPullRequestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_container_pull_request, container, false)
        viewModel = ViewModelProviders.of(this).get(ContainerPullRequestViewModel::class.java)

        binding.let {
            it.viewModel = viewModel
            it.handler = this
        }

        setToolbar(binding)

        return binding.root
    }

    private fun setToolbar(binding: FragmentContainerPullRequestBinding) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.root.toolbarPullrequest)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        activity!!.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

}
