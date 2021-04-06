package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.view


sealed class LoginViewState {

    data class IsLoading(val data: Boolean) : LoginViewState()
    data class SuccessOnLoginIn(val data: Boolean = true) : LoginViewState()
    data class SuccessOnCreatingAccount(val data: Boolean = true) : LoginViewState()

}