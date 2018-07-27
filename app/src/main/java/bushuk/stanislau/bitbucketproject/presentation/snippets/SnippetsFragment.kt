package bushuk.stanislau.bitbucketproject.presentation.snippets


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
import bushuk.stanislau.bitbucketproject.adapters.RecyclerSnippetsAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentSnippetsBinding
import kotlinx.android.synthetic.main.fragment_snippets.*


class SnippetsFragment : Fragment() {

    lateinit var binding: FragmentSnippetsBinding

    lateinit var viewModel: SnippetsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_snippets, container, false)

        viewModel = ViewModelProviders.of(this).get(SnippetsViewModel::class.java)

        binding.let {
            it.modelFollow = viewModel.loadingModel
            it.setLifecycleOwner(this)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snippets_screen_recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = RecyclerSnippetsAdapter()
        snippets_screen_recycler.adapter = adapter
        viewModel.snippets.observe(this, Observer(adapter::submitList))
    }
}
