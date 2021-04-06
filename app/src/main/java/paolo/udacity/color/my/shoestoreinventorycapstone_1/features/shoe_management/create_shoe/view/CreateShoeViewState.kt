package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.create_shoe.view


sealed class CreateShoeViewState {

    data class IsLoading(val data: Boolean) : CreateShoeViewState()
    data class SuccessOnCreatingShoe(val data: Boolean = false) : CreateShoeViewState()
    data class SuccessOnCancellingShoeCreation(val data: Boolean = false) : CreateShoeViewState()

}