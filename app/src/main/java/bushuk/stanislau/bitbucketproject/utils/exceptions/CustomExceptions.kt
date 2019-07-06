package bushuk.stanislau.bitbucketproject.utils.exceptions


sealed class CustomExceptions(override val message: String) : Exception() {
    object ServerError : CustomExceptions("SERVER DIED")
    object SocketTimeOut : CustomExceptions("TIMEOUT")
    object UnAuthorized : CustomExceptions("UNAUTHORIZED")
    object BadBody : CustomExceptions("BAD BODY")
    data class ANOTHER(override val message: String) : CustomExceptions(message)
}

