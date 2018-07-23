package bushuk.stanislau.bitbucketproject.presentation.repositories


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
import bushuk.stanislau.bitbucketproject.adapters.RecyclerAdapterRepositories
import bushuk.stanislau.bitbucketproject.databinding.FragmentRepositoriesBinding
import bushuk.stanislau.bitbucketproject.presentation.repositories.viewModel.RepositoriesViewModel
import kotlinx.android.synthetic.main.fragment_repositories.*
import timber.log.Timber

class RepositoriesFragment : Fragment() {

    lateinit var viewModel: RepositoriesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentRepositoriesBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_repositories, container, false)
        viewModel = ViewModelProviders.of(this).get(RepositoriesViewModel::class.java)

        binding.let {
            it.viewModelRepositories = viewModel
            it.setLifecycleOwner(this)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repositories_screen_repositories_recycler.layoutManager = LinearLayoutManager(activity)
        repositories_screen_repositories_recycler.adapter = RecyclerAdapterRepositories(viewModel.getRepositories())

        viewModel.getRepositories().observe(this, Observer {
            Timber.e("NOTIFY")
            repositories_screen_repositories_recycler.adapter!!.notifyDataSetChanged()
        })
    }
}
