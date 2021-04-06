package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.di

import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.mapper.UserMapper


internal val authCommonPartModule = module {

    factory { UserMapper() }

}
