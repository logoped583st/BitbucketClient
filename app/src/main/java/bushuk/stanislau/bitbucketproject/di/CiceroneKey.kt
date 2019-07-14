package bushuk.stanislau.bitbucketproject.di

import dagger.MapKey

@MustBeDocumented
@Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class CiceroneKey(val value: Cicerones)

enum class Cicerones {
    GLOBAL,
    DRAWER
}