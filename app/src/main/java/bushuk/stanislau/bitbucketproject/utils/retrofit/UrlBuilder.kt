package bushuk.stanislau.bitbucketproject.utils.retrofit

import timber.log.Timber

object UrlBuilder {


    private var pullRequestName: String? = null
    var pullRequestSort: String = "-id"
    private var pullRequestState: String? = "(state=\"OPEN\")"

    var repositoryPath: String? = null


    fun queryLanguageBuilder(language: String?): String? {
        Timber.e(language)
        return if (language == "All" || language == null) {
            ""
        } else {
            "AND(language=\"$language\")"
        }
    }

    fun queryAccessBuilder(access: String?): String? {
        return when (access) {
            "All" -> ""

            "Private" -> "AND(is_private=" + true + ")"

            "Public" -> "AND(is_private=" + false + ")"

            else -> {
                null
            }
        }
    }

    fun buildQuery(repositoryName: String?, repositoryAccess: String?, repositoryLanguage: String?): String {
        var query = ""
        query += if (repositoryName.isNullOrEmpty()) {
            "(name~\"\")"
        } else {
            "(name~\"$repositoryName\")"
        }

        if (!(repositoryLanguage).isNullOrEmpty()) {
            query += queryLanguageBuilder(repositoryLanguage)
        }

        if (!repositoryAccess.isNullOrEmpty()) {
            query += queryAccessBuilder(repositoryAccess)
        }

        return query
    }


    fun buildPathWithHash(path: String, hash: String) {
        repositoryPath = when (path) {
            "src" -> "$hash/"

            else -> "$hash/$path/"
        }
    }

    fun resetCodePath() {
        repositoryPath = null
    }


    fun pullRequestNameBuilder(name: String) {
        pullRequestName = "(title~\"$name\")"
    }

    fun parseState(): String = when (pullRequestState) {
        "(state=\"OPEN\")" -> "Open"

        "(state=\"MERGED\")" -> "Merged"

        "(state=\"DECLINED\")" -> "Declined"

        "(state=\"OPEN\") OR (state=\"MERGED\") OR (state=\"DECLINED\")" -> "All"

        else -> ""
    }

    fun parseSort(): String = when (pullRequestSort) {
        "-id" -> "Id up"

        "id" -> "Id down"

        "-updated_on" -> "Update time up"

        "updated_on" -> "Update time down"

        else -> null.toString()
    }


    fun pullRequestStateBuilder(state: String) {
        val upperState = state.toUpperCase()
        pullRequestState = when (upperState) {
            "OPEN" -> "(state=\"OPEN\")"

            "MERGED" -> "(state=\"MERGED\")"

            "DECLINED" -> "(state=\"DECLINED\")"

            "ALL" -> "(state=\"OPEN\") OR (state=\"MERGED\") OR (state=\"DECLINED\")"

            else -> null
        }
    }

    fun buildQueryPullRequest(): String = if (pullRequestName == null) {
        pullRequestState!!
    } else {
        pullRequestName + "AND" + pullRequestState
    }


    fun buildSortPullRequest(sort: String) {
        pullRequestSort = when (sort) {
            "Id up" -> "-id"

            "Id down" -> "id"

            "Update time up" -> "-updated_on"

            "Update time down" -> "updated_on"

            else -> null.toString()
        }
    }


    fun resetQueryAfterRoute() {
        pullRequestName = null
        pullRequestSort = "-id"
        pullRequestState = "(state=\"OPEN\")"
    }

}