package bushuk.stanislau.bitbucketproject.utils.CryptUtils

interface Crypto {
    fun encrypt(byteArray: ByteArray): ByteArray

    fun decrypt(byteArray: ByteArray): ByteArray
}