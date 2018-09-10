package bushuk.stanislau.bitbucketproject.room.pullrequest

import bushuk.stanislau.bitbucketproject.room.code.Branch
import bushuk.stanislau.bitbucketproject.room.commits.Commit

data class PullRequestSource(val branch: Branch, val commit: Commit)