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
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet
import kotlinx.android.synthetic.main.fragment_snippets.*


class SnippetsFragment : Fragment(), ClickFollow {
    override fun onClickItem(view: View, data: Any) {
        viewModel.navigateToCode((data as Snippet).links.self.href)
    }

    lateinit var binding: FragmentSnippetsBinding

    lateinit var viewModel: SnippetsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_snippets, container, false)
        viewModel = ViewModelProviders.of(this).get(SnippetsViewModel::class.java)

        binding.let {
            it.viewModel = viewModel
            it.setLifecycleOwner(this)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snippets_screen_recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = RecyclerSnippetsAdapter()
        adapter.clickFollow = this
        snippets_screen_recycler.adapter = adapter
        viewModel.snippets.observe(this, Observer(adapter::submitList))
    }
}
