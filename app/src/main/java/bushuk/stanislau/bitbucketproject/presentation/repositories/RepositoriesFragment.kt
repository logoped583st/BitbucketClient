package bushuk.stanislau.bitbucketproject.presentation.repositories


import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.SearchView
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerRepositoriesAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentRepositoriesBinding
import kotlinx.android.synthetic.main.fragment_repositories.*
import timber.log.Timber

class RepositoriesFragment : Fragment(), LifecycleOwner {

    lateinit var viewModel: RepositoriesViewModel
    private lateinit var searchView: SearchView
    private var test: Boolean = false
    lateinit var binding: FragmentRepositoriesBinding

    private lateinit var adapter: RecyclerRepositoriesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_repositories, container, false)
        viewModel = ViewModelProviders.of(this).get(RepositoriesViewModel::class.java)

        binding.let {
            it.viewModelRepositories = viewModel
            it.setLifecycleOwner(this)
        }

        setHasOptionsMenu(true)

        if (savedInstanceState == null) {
            test = true
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repositories_screen_recycler.layoutManager = LinearLayoutManager(activity)
        adapter = RecyclerRepositoriesAdapter()
        repositories_screen_recycler.adapter = adapter

        if (savedInstanceState == null) {
            viewModel.repositoriesDataSourceFactory.repositoriesDataSource.query = HashMap()
        }

        viewModel.repositories.observe(this, Observer(adapter::submitList))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Timber.e("CREATE OPTIONS")
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()
        inflater.inflate(R.menu.main_screen, menu)
        val myActionMenuItem: MenuItem = menu.findItem(R.id.action_search)
        searchView = myActionMenuItem.actionView as SearchView

        if (!viewModel.searchText.isNullOrEmpty()) {
            searchView.onActionViewExpanded()
            searchView.clearFocus()
            searchView.setQuery(viewModel.searchText, true)
        }

        searchView.setIconifiedByDefault(true)

        viewModel.observeSearchView(searchView, this, adapter)

    }


}
