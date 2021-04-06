package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.mapper.MainFailureMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.view.MainViewModel


internal val mainPartModule = module {

    factory { MainFailureMapper() }

    viewModel { MainViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get(), get()) }

}