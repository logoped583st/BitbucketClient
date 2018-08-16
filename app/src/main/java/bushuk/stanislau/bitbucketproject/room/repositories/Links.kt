package bushuk.stanislau.bitbucketproject.room.repositories

import bushuk.stanislau.bitbucketproject.room.Href

data class Links(val self: Href,
                 val html: Href,
                 val avatar: Href,
                 val branches: Href,
                 val tags: Href,
                 val source: Href,
                 val pullRequests: Href)