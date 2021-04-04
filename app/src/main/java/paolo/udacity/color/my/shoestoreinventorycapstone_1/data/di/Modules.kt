package paolo.udacity.color.my.shoestoreinventorycapstone_1.data.di

import org.koin.core.module.Module
import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.data_source.local.UserLocalDataSource
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.mapper.UserLocalMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.repository.AuthenticationDataRepository
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository.AuthenticationRepository
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.listByElementsOf


val dataModules by lazy {
    listByElementsOf<Module>(
        repositoriesModules
    )
}

internal val repositoriesModules by lazy {
    listByElementsOf<Module>(
        authenticationModule
    )
}

internal val authenticationModule = module {
    single { UserLocalDataSource(get()) }

    factory { UserLocalMapper() }

    single<AuthenticationRepository> { AuthenticationDataRepository(get(), get()) }
}