package bushuk.stanislau.bitbucketproject.room.pullrequest

import bushuk.stanislau.bitbucketproject.room.repositories.Owner
import bushuk.stanislau.bitbucketproject.room.user.User
import java.util.*

data class PullRequest(val title: String,
                       val description: String,
                       val id: Int,
                       val links: PullRequestLinks,
                       val destination: PullRequestDestination,
                       val updated_on: Date,
                       val source: PullRequestSource,
                       val author: Owner,
                       val reviewers: MutableList<User>,
                       val state: String)