package paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.di

import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.authentication.IsUserLoggedUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.main.LogOutUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.authentication.AttemptLoginUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.authentication.CreateUserUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.main.GetUserUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.shoe_management.RegisterShoeUseCase


internal val authenticationUseCasesModule = module {

    factory { AttemptLoginUseCase(get()) }
    factory { CreateUserUseCase(get()) }
    factory { IsUserLoggedUseCase(get()) }

}

internal val mainUseCasesModule = module {

    factory { GetUserUseCase(get()) }
    factory { LogOutUseCase(get()) }

}

internal val shoeManagementUseCasesModule = module {

    factory { RegisterShoeUseCase(get()) }

}