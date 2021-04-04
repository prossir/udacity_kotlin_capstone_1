package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.di

import org.koin.core.module.Module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.instructions.di.instructionsPartModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.di.listShoesPartModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.main.di.mainPartModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.view_shoe_detail.di.viewShoeDetailPartModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.listByElementsOf


val shoeManagementFeatureModule by lazy {
    listByElementsOf<Module>(
        instructionsPartModule,
        listShoesPartModule,
        mainPartModule,
        viewShoeDetailPartModule,
    )
}
