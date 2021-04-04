package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.mapper.LoginFailureMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.view.LoginViewModel


internal val loginPartModule = module {

    factory { LoginFailureMapper() }

    viewModel { LoginViewModel(get(), get(), get(), get()) }

}