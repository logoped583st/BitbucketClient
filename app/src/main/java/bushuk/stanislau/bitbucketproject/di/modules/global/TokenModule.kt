package bushuk.stanislau.bitbucketproject.di.modules.global

import bushuk.stanislau.bitbucketproject.global.ITokenCache
import bushuk.stanislau.bitbucketproject.global.TokenCache
import bushuk.stanislau.bitbucketproject.presentation.auth.AccessRepository
import bushuk.stanislau.bitbucketproject.presentation.auth.IAccessRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class TokenModule {

    @Binds
    @Singleton
    abstract fun bindTokenCache(tokenCache: TokenCache): ITokenCache

    @Binds
    @Singleton
    abstract fun bindUserAccessRepository(userAccessRepository: AccessRepository): IAccessRepository
}
