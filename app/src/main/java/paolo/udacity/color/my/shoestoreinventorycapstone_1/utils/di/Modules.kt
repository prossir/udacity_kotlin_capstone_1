package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.di

import org.koin.core.module.Module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.di.databaseModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.listByElementsOf
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.provider.di.provideModule


val utilsModules by lazy {
    listByElementsOf<Module>(
        provideModule,
        databaseModule
    )
}