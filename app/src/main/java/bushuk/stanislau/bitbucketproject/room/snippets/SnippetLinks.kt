package bushuk.stanislau.bitbucketproject.room.snippets

import bushuk.stanislau.bitbucketproject.room.Href

data class SnippetLinks(val watchers: Href,
                        val commits: Href,
                        val self: Href,
                        val comments: Href,
                        val patch: Href,
                        val diff: Href)