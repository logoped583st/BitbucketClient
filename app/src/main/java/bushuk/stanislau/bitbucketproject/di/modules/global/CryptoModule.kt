package bushuk.stanislau.bitbucketproject.di.modules.global

import android.content.Context
import bushuk.stanislau.bitbucketproject.utils.cryptUtils.CryptApi19
import bushuk.stanislau.bitbucketproject.utils.cryptUtils.CryptApi23
import bushuk.stanislau.bitbucketproject.utils.cryptUtils.Crypto
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CryptoModule(val context: Context) {

    @Provides
    @Singleton
    fun provideCtypto(): Crypto {
        val version = android.os.Build.VERSION.SDK_INT
        val crypto: Crypto

        if (version >= 23) {
            crypto = CryptApi23(context)
        } else {
            crypto = CryptApi19(context)
        }

        return crypto
    }


}