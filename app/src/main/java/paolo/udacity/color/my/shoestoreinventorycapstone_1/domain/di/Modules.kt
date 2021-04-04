package paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.di

import org.koin.core.module.Module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.di.authenticationUseCasesModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.di.mainUseCasesModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.di.shoeManagementUseCasesModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.listByElementsOf


internal val domainModules by lazy {
    listByElementsOf<Module>(
        useCasesModules
    )
}

private val useCasesModules by lazy {
    listOf(
        authenticationUseCasesModule,
        mainUseCasesModule,
        shoeManagementUseCasesModule
    )
}