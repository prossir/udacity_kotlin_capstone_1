package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.di

import org.koin.core.module.Module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.di.authenticationFeatureModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.di.shoeManagementFeatureModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.listByElementsOf


val featuresModules by lazy {
    listByElementsOf<Module>(
        authenticationFeatureModule,
        shoeManagementFeatureModule
    )
}