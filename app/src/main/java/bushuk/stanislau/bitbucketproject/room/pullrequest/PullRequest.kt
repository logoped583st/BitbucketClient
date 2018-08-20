package bushuk.stanislau.bitbucketproject.room.pullrequest

import bushuk.stanislau.bitbucketproject.room.repositories.Owner

data class PullRequest(val title: String,
                       val description: String,
                       val id: Int,
                       val links: PullRequestLinks,
                       val destination: PullRequestDestination,
                       val source: PullRequestSource,
                       val author: Owner,
                       val state: String)