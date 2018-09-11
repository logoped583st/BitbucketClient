package bushuk.stanislau.bitbucketproject.room.snippets

import bushuk.stanislau.bitbucketproject.room.repositories.Owner
import java.util.*

data class Snippet(val title: String,
                   val created_on: Date,
                   val is_private: Boolean,
                   val id: String,
                   val files: Map<String, links>,
                   val links: SnippetLinks,
                   val owner: Owner)