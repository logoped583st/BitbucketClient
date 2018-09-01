package bushuk.stanislau.bitbucketproject.room.pullrequest

import bushuk.stanislau.bitbucketproject.room.user.User

data class PullRequestParticipants(val approved: Boolean,
                                   val user: User,
                                   val role: String)