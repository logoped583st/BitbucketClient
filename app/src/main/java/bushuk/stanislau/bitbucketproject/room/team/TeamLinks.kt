package bushuk.stanislau.bitbucketproject.room.team

import bushuk.stanislau.bitbucketproject.room.Href

data class TeamLinks(
        val hooks: Href,
        val self: Href,
        val repositories: Href,
        val avatar: Href,
        val members: Href,
        val projects: Href,
        val snippets: Href
)