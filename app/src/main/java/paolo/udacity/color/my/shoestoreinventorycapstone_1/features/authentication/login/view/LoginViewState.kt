package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.view

import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.presentation.model.FailureModel


sealed class LoginViewState {

    data class IsLoading(val data: Boolean) : LoginViewState()
    data class SuccessOnLoginIn(val data: Boolean = true) : LoginViewState()
    data class SuccessOnCreatingAccount(val data: Boolean = true) : LoginViewState()
    data class Failure(val failure: FailureModel) : LoginViewState()

}