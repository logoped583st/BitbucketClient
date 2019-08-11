package bushuk.stanislau.bitbucketproject.presentation.team

import javax.inject.Inject

class TeamsQueryModel @Inject constructor() : ITeamsQueryModel {

    override val teamRole: String
        get() = role.role

    var role: TeamRole = TeamRole.MEMBER

    enum class TeamRole(val role: String) {
        ADMIN("admin"),
        CONTRIBUTER("contributor"),
        MEMBER("member")
    }
}

interface ITeamsQueryModel {

    val teamRole: String
}