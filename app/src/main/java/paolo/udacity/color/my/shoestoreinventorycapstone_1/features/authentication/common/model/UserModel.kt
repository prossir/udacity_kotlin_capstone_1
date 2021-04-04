package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.model


data class UserModel(
    var email: String,
    var password: String,
    val isLogged: Boolean = false
)