package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.splash.view

import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.presentation.model.FailureModel


sealed class SplashViewState {

    data class SuccessInGettingUserLogged(val data: Boolean) : SplashViewState()
    data class Failure(val failure: FailureModel) : SplashViewState()

}