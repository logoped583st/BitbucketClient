package bushuk.stanislau.bitbucketproject.presentation.team

import bushuk.stanislau.bitbucketproject.presentation.base.BaseDataSourceFactory
import bushuk.stanislau.bitbucketproject.room.team.Team
import bushuk.stanislau.bitbucketproject.room.team.TeamResponse
import javax.inject.Inject
import javax.inject.Provider

class TeamsDataSourceFactory @Inject constructor(
        teamDataSource: Provider<TeamsDataSource>)
    : BaseDataSourceFactory<Team, TeamResponse>(TODO())

