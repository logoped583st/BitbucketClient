package bushuk.stanislau.bitbucketproject.presentation.team

import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.api.Api
import bushuk.stanislau.bitbucketproject.global.IUserModel
import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSource
import bushuk.stanislau.bitbucketproject.room.team.Team
import bushuk.stanislau.bitbucketproject.room.team.TeamResponse
import io.reactivex.Single
import javax.inject.Inject

class TeamsDataSource @Inject constructor(
        private val api: Api,
        private val queryModel: TeamsQueryModel,
        userModel: IUserModel)
    : BaseDataSource<Team, TeamResponse>() {
    override fun onResultInitial(value: TeamResponse, callback: LoadInitialCallback<String, Team>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResult(value: TeamResponse, callback: LoadCallback<String, Team>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val errorText: String = App.resourcesApp.getString(R.string.repositories_screen_no_repositories)

    override val single: Single<TeamResponse> = userModel.user
            .firstOrError()
            .flatMap { api.getTeams(it.username) }

    override fun loadNextPage(url: String): Single<TeamResponse> = api.getTeams(url)

}