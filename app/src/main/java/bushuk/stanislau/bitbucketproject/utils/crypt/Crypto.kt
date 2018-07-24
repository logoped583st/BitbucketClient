package bushuk.stanislau.bitbucketproject.utils.crypt

interface Crypto {
    fun encrypt(byteArray: ByteArray): ByteArray

    fun decrypt(byteArray: ByteArray): ByteArray
}