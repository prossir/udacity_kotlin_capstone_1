package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.di

import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.mapper.ShoeMapper


internal val shoeCommonPartModule = module {

    factory { ShoeMapper() }

}