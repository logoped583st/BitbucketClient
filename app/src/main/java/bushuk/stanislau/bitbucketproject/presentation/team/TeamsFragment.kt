package bushuk.stanislau.bitbucketproject.presentation.team


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import bushuk.stanislau.bitbucketproject.Injectable
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.FragmentTeamsBinding
import bushuk.stanislau.bitbucketproject.presentation.base.BaseListFragment
import bushuk.stanislau.bitbucketproject.room.team.Team
import bushuk.stanislau.bitbucketproject.room.team.TeamResponse
import kotlinx.android.synthetic.main.fragment_teams.*
import javax.inject.Inject

class TeamsFragment : BaseListFragment<Team, TeamResponse, TeamsViewModel, FragmentTeamsBinding>(), Injectable {

    override val itemLayout: Int = R.layout.team_item

    override fun onClickItem(view: View, data: Team) {

    }

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val containerId: Int = R.layout.fragment_teams
    override val viewModelClass: Class<TeamsViewModel> = TeamsViewModel::class.java
    override val scope: ViewModelScope = ViewModelScope.ACTIVITY


    override fun applyBinding() {
        binding.viewGroup = list_constraint
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    fun clickFilterFab(view: View) {
        //repositories_screen_settings_menu.close(true)
        //repositories_screen_slide_panel.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
    }

}
