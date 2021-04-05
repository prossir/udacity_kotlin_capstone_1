package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.view

import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.model.ShoeModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.presentation.model.FailureModel


sealed class ListShoesViewState {

    data class IsLoading(val data: Boolean) : ListShoesViewState()
    data class SuccessOnGettingRegisteredShoes(val data: List<ShoeModel>) : ListShoesViewState()
    data class Failure(val failure: FailureModel) : ListShoesViewState()
    data class GoToAddShoes(val data: Boolean = true) : ListShoesViewState()

}