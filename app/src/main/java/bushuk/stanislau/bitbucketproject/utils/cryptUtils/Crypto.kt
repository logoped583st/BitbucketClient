package bushuk.stanislau.bitbucketproject.utils.cryptUtils

interface Crypto {
    fun encrypt(byteArray: ByteArray): ByteArray

    fun decrypt(byteArray: ByteArray): ByteArray
}