package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.di

import org.koin.core.module.Module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.di.shoeCommonPartModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.create_shoe.di.createShoePartModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.instructions.di.instructionsPartModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.di.listShoesPartModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.di.mainPartModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.listByElementsOf


val shoeManagementFeatureModule by lazy {
    listByElementsOf<Module>(
            shoeCommonPartModule,
            createShoePartModule,
            instructionsPartModule,
            listShoesPartModule,
            mainPartModule
    )
}