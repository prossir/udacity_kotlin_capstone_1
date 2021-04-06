package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.create_shoe.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.create_shoe.mapper.CreateShoeFailureMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.create_shoe.view.CreateShoeViewModel


internal val createShoePartModule = module {

    factory { CreateShoeFailureMapper() }

    viewModel { CreateShoeViewModel(get(), get(), get()) }

}