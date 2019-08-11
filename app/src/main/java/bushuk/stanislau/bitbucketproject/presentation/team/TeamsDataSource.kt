package bushuk.stanislau.bitbucketproject.presentation.team

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.presentation.base.IBaseDataSource
import bushuk.stanislau.bitbucketproject.room.team.Team
import bushuk.stanislau.bitbucketproject.room.team.TeamResponse
import io.reactivex.Single
import javax.inject.Inject

class TeamsDataSource @Inject constructor(
        private val api: Api,
        teamsQueryModel: ITeamsQueryModel)
    : BaseDataSource<Team, TeamResponse>(), ITeamsDataSource {


    override val errorText: String = App.resourcesApp.getString(R.string.repositories_screen_no_repositories)

    override val single: Single<TeamResponse> = api.getTeams(teamsQueryModel.teamRole)

    override fun loadNextPage(url: String): Single<TeamResponse> = api.getTeams(url)

}

interface ITeamsDataSource : IBaseDataSource<TeamResponse, Team>