package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.mapper.ListShoesFailureMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.view.ListShoesViewModel


internal val listShoesPartModule = module {

    factory { ListShoesFailureMapper() }

    viewModel { ListShoesViewModel(get(), get(), get()) }

}