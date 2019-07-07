package bushuk.stanislau.bitbucketproject.di.modules.global

import android.content.Context
import bushuk.stanislau.bitbucketproject.utils.crypt.BaseCrypto
import bushuk.stanislau.bitbucketproject.utils.crypt.CryptApi19
import bushuk.stanislau.bitbucketproject.utils.crypt.CryptApi23
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CryptoModule() {

    @Provides
    fun provideCrypto19(context: Context): CryptApi19 {
        return CryptApi19(context)
    }

    @Provides
    fun provideCrypto23(crypto: CryptApi19,context: Context): CryptApi23 {
        return CryptApi23(crypto, context)
    }

    @Singleton
    @Provides
    fun crypt(crypto: CryptApi23): BaseCrypto {
        return BaseCrypto(crypto)
    }
}