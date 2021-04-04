package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.splash.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.splash.mapper.IsUserLoggedFailureMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.splash.view.SplashViewModel


internal val splashPartModule = module {

    factory { IsUserLoggedFailureMapper() }

    viewModel { SplashViewModel(get(), get()) }

}