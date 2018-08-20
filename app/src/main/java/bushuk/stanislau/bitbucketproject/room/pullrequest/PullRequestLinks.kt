package bushuk.stanislau.bitbucketproject.room.pullrequest

import bushuk.stanislau.bitbucketproject.room.Href

data class PullRequestLinks(val decline: Href,
                            val commits: Href,
                            val comments: Href,
                            val merge: Href,
                            val activity: Href,
                            val approve: Href)