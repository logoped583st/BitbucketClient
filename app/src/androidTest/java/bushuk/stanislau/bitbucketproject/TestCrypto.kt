package bushuk.stanislau.bitbucketproject

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import bushuk.stanislau.bitbucketproject.utils.crypt.BaseCrypto
import bushuk.stanislau.bitbucketproject.utils.crypt.CryptApi19
import bushuk.stanislau.bitbucketproject.utils.crypt.CryptApi23
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestCrypto {
    val crypto = BaseCrypto(CryptApi23(CryptApi19(InstrumentationRegistry.getInstrumentation().context), InstrumentationRegistry.getInstrumentation().context))
    @Test
    fun test_crypto() {

        val encrypted = crypto.encrypt("test123__test123")

        val decrypted = crypto.decrypt(encrypted)

        Assert.assertEquals("test123__test123", decrypted)

    }

    @Test
    fun test_jwt(){
        val encrypted = crypto.encrypt("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2Mzh9.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI")

        val decrypted = crypto.decrypt(encrypted)

        Assert.assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2Mzh9.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI", decrypted)    }
}
