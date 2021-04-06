package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.instructions.view


sealed class InstructionsViewState {

    data class GoToShoeList(val data: Boolean = true) : InstructionsViewState()

}