package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.welcome.view


sealed class WelcomeViewState {

    data class GoToInstructions(val data: Boolean = true) : WelcomeViewState()

}