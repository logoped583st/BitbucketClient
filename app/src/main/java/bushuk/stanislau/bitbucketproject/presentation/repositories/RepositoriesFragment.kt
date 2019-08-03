package bushuk.stanislau.bitbucketproject.presentation.repositories


import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import bushuk.stanislau.bitbucketproject.Injectable
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.RecyclerScrollFab
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.constants.ListOfLanguages
import bushuk.stanislau.bitbucketproject.databinding.FragmentRepositoriesBinding
import bushuk.stanislau.bitbucketproject.presentation.base.BaseListFragment
import bushuk.stanislau.bitbucketproject.room.repositories.RepositoriesResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Repository
import bushuk.stanislau.bitbucketproject.utils.extensions.spinnerRx
import com.github.clans.fab.FloatingActionMenu
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.fragment_repositories.*
import javax.inject.Inject

class RepositoriesFragment : BaseListFragment<Repository, RepositoriesResponse, RepositoriesViewModel, FragmentRepositoriesBinding>(), Injectable {


    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val containerId: Int = R.layout.fragment_repositories
    override val viewModelClass: Class<RepositoriesViewModel> = RepositoriesViewModel::class.java
    override val scope: ViewModelScope = ViewModelScope.ACTIVITY

    override fun applyBinding() {
        binding.viewModel = viewModel
        binding.fragment = this
    }

    private val access: List<String> = listOf("All", "Public", "Private")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accessSpinner()
        languageSpinner()


        list_constraint.mBinding.rv.addOnScrollListener(object : RecyclerScrollFab() {
            override fun getFab(): FloatingActionMenu = repositories_screen_settings_menu
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
