package bushuk.stanislau.bitbucketproject.presentation.repositories


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerAdapterRepositories
import bushuk.stanislau.bitbucketproject.presentation.repositories.viewModel.RepositoriesViewModel
import kotlinx.android.synthetic.main.fragment_repositories.*

class RepositoriesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_repositories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(RepositoriesViewModel::class.java)
        repositories_screen_repositories_recycler.layoutManager=LinearLayoutManager(activity)
        repositories_screen_repositories_recycler.adapter = RecyclerAdapterRepositories(listOf("asd","Asd","asd","asd","Asd","asd","asd","Asd","asd"))
    }
}
