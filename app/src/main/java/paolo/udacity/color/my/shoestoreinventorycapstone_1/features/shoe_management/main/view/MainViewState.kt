package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.main.view

import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.model.UserModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.presentation.model.FailureModel


sealed class MainViewState {

    data class SuccessInGettingUser(val data: UserModel) : MainViewState()
    data class SuccessInLogOut(val success: Boolean = true) : MainViewState()
    data class Failure(val failure: FailureModel) : MainViewState()

}