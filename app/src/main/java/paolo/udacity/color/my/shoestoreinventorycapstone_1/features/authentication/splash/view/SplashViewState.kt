package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.splash.view


sealed class SplashViewState {

    data class SuccessInGettingUserLogged(val data: Boolean) : SplashViewState()

}