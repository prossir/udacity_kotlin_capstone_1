package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.main.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.mapper.ListShoesFailureMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.main.view.MainViewModel


internal val mainPartModule = module {

    factory { ListShoesFailureMapper() }

    viewModel { MainViewModel(get(), get(), get(), get()) }

}