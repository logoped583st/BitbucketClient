package bushuk.stanislau.bitbucketproject.presentation.snippets


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentSnippetsBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.snippets.Snippet
import kotlinx.android.synthetic.main.fragment_snippets.*


class SnippetsFragment : Fragment(), ClickFollow<Snippet> {
    override fun onClickItem(view: View, data: Snippet) {
        viewModel.navigateToCode(data.links.self.href)
    }

    lateinit var binding: FragmentSnippetsBinding

    lateinit var viewModel: SnippetsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_snippets, container, false)
        viewModel = ViewModelProviders.of(this).get(SnippetsViewModel::class.java)

        binding.let {
            it.lifecycleOwner = this
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snippets_screen_recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = RecyclerAdapter(this)
        snippets_screen_recycler.adapter = adapter
        viewModel.snippets.observe(this, Observer(adapter::submitList))
    }
}
