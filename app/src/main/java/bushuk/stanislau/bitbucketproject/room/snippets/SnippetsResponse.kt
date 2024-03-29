package bushuk.stanislau.bitbucketproject.room.snippets

import bushuk.stanislau.bitbucketproject.room.BaseListResponse
import bushuk.stanislau.bitbucketproject.room.ItemResponse
import bushuk.stanislau.bitbucketproject.room.repositories.Owner
import java.util.*

class SnippetsResponse : BaseListResponse<Snippet>()

data class Snippet(val title: String,
                   val created_on: Date,
                   val is_private: Boolean,
                   val id: String,
                   val files: Map<String, links>,
                   val links: SnippetLinks,
                   val owner: Owner) : ItemResponse()