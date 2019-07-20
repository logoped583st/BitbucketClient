package bushuk.stanislau.bitbucketproject.presentation.repositories


import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bushuk.stanislau.bitbucketproject.Injectable
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.RecyclerScrollFab
import bushuk.stanislau.bitbucketproject.adapters.RecyclerAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.constants.ListOfLanguages
import bushuk.stanislau.bitbucketproject.databinding.FragmentRepositoriesBinding
import bushuk.stanislau.bitbucketproject.presentation.base.BaseBindingFragment
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import bushuk.stanislau.bitbucketproject.utils.extensions.spinnerRx
import com.github.clans.fab.FloatingActionMenu
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.base_list_constraint.*
import kotlinx.android.synthetic.main.fragment_repositories.*
import javax.inject.Inject

class RepositoriesFragment : BaseBindingFragment<RepositoriesViewModel, FragmentRepositoriesBinding>(), LifecycleOwner, ClickFollow<Repository>, Injectable {

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val containerId: Int = R.layout.fragment_repositories
    override val viewModelClass: Class<RepositoriesViewModel> = RepositoriesViewModel::class.java
    override val scope: ViewModelScope = ViewModelScope.ACTIVITY

    override fun applyBinding() {
        binding.viewModel = viewModel
        binding.fragment = this
    }

    private val access: MutableList<String> = mutableListOf("All", "Public", "Private")
    private lateinit var adapter: RecyclerAdapter<Repository>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewGroup = list_constraint

        adapter = RecyclerAdapter(this)
        accessSpinner()
        languageSpinner()
        rv.adapter = adapter


        rv.addOnScrollListener(object : RecyclerScrollFab() {
            override fun getFab(): FloatingActionMenu = repositories_screen_settings_menu
        })


        viewModel.dataSource.observe(this, Observer {
            adapter.submitList(it)
        })

        repositories_screen_slide_constraint.setOnClickListener {
            //Block closing
        }

        repositories_screen_slide_panel.setFadeOnClickListener {
            repositories_screen_slide_panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
    }


//    override fun onBackPressed() {
//        if ((repositories_screen_slide_panel != null)) {
//            if (repositories_screen_slide_panel.panelState != SlidingUpPanelLayout.PanelState.COLLAPSED) {
//                repositories_screen_slide_panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
//            } else {
//                viewModel.exitFromFragment()
//            }
//        } else {
//            viewModel.exitFromFragment()
//        }
//    }


    override fun onClickItem(view: View, data: Repository) {
        //waiting_message.show(repositories_screen_content)
        //viewModel.navigateToRepositoryScreen(data, viewModel.factory.repositoriesDataSource.userModel.user.value!!.username)
    }

//    override fun onAttach(activity: Activity?) {
//        super.onAttach(activity)
//        if (activity is MainScreenActivity) {
//            activity.setBackPress(this)
//        }
//    }


    private fun languageSpinner() {
        val languageSpinnerAdapter: ArrayAdapter<String> = SpinnerAdapter(context, android.R.layout.simple_spinner_item, ListOfLanguages.listOfLanguages())
        languageSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        repositories_screen_spinner_language.adapter = languageSpinnerAdapter

        repositories_screen_spinner_language.spinnerRx {
            viewModel.repositoryLanguage = it
        }
    }

    private fun accessSpinner() {
        val accessSpinnerAdapter: ArrayAdapter<String> = SpinnerAdapter(activity!!, android.R.layout.simple_spinner_item, access)
        accessSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        repositories_screen_spinner_access.adapter = accessSpinnerAdapter
        repositories_screen_spinner_access.spinnerRx {
            viewModel.repositoryAccess = it
        }
    }

    fun clickFilterFab(view: View) {
        repositories_screen_settings_menu.close(true)
        repositories_screen_slide_panel.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
    }

}
