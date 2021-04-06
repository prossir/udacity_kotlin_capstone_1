package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.di

import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.mapper.UserMapper


internal val loginPartModule = module {

    factory { UserMapper() }

}
