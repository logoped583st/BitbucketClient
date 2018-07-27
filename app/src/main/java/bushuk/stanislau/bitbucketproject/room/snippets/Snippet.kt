package bushuk.stanislau.bitbucketproject.room.snippets

import bushuk.stanislau.bitbucketproject.room.repositories.Owner
import bushuk.stanislau.bitbucketproject.room.user.Links
import java.util.*

data class Snippet(val title: String,
                   val created_on: Date,
                   val is_private: Boolean,
                   val id: String,
                   val owner: Owner)