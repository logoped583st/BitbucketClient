package bushuk.stanislau.bitbucketproject.presentation.repositories

import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoriesQueryModel @Inject constructor() {

    var query: String? = null

    init {
        Timber.e("INIT QUERY")
    }


    fun buildQuery(repositoryName: String?, repositoryAccess: String?, repositoryLanguage: String?) {
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

        this.query = query
    }

    private fun queryLanguageBuilder(language: String?): String? {
        Timber.e(language)
        return if (language == "All" || language == null) {
            ""
        } else {
            "AND(language=\"$language\")"
        }
    }

    private fun queryAccessBuilder(access: String?): String? {
        return when (access) {
            "All" -> ""

            "Private" -> "AND(is_private=" + true + ")"

            "Public" -> "AND(is_private=" + false + ")"

            else -> {
                null
            }
        }
    }

}