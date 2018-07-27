package bushuk.stanislau.bitbucketproject.room.snippets

data class SnippetsResponce(val next: String,
                            val size: Int,
                            val previous: String,
                            val values: MutableList<Snippet>)