package paolo.udacity.color.my.shoestoreinventorycapstone_1.data.di

import org.koin.core.module.Module
import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.data_source.local.ShoeLocalDataSource
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.data_source.local.UserLocalDataSource
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.mapper.ShoeLocalMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.mapper.UserLocalMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.repository.AuthenticationDataRepository
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.repository.ShoeManagementDataRepository
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository.AuthenticationRepository
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository.ShoeManagementRepository
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.listByElementsOf


val dataModules by lazy {
    listByElementsOf<Module>(
        repositoriesModules
    )
}

internal val repositoriesModules by lazy {
    listByElementsOf<Module>(
            shoeManagementModule,
            authenticationModule
    )
}

internal val shoeManagementModule = module {
    single { ShoeLocalDataSource(get()) }

    factory { ShoeLocalMapper() }

    single<ShoeManagementRepository> { ShoeManagementDataRepository(get(), get(), get()) }
}

internal val authenticationModule = module {
    single { UserLocalDataSource(get()) }

    factory { UserLocalMapper() }

    single<AuthenticationRepository> { AuthenticationDataRepository(get(), get()) }
}