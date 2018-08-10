package bushuk.stanislau.bitbucketproject.utils.retrofit

import timber.log.Timber

object UrlBuilder {

    private var query: String = ""
    private var repositoryName: String? = null
    private var repositoryLanguage: String? = null
    private var repositoryAccess: String? = null

    fun queryRepositoryNameBuilder(search: String?) {
        repositoryName = "(name~\"$search\")"
    }

    fun queryLanguageBuilder(language: String) {
        Timber.e(language)
        repositoryLanguage = if (language == "All") {
            null
        } else {
            "(language=\"$language\")"
        }
    }

    fun queryAccessBuilder(access: String) {
        repositoryAccess = when (access) {
            "All" -> null

            "Private" -> "(is_private=" + true + ")"

            "Public" -> "(is_private=" + false + ")"

            else -> {
                null
            }
        }

    }

    fun buildQuery(): String {
        query = ""
        query += if (repositoryName.isNullOrEmpty()) {
            "(name~\"\")"
        } else {
            repositoryName
        }

        if (!repositoryLanguage.isNullOrEmpty()) {
            query += "AND$repositoryLanguage"
        }

        if (!repositoryAccess.isNullOrEmpty()) {
            query += "AND$repositoryAccess"
        }

        return query
    }
}