package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.mapper

import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.entity.User
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.model.UserModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.mapper.Mapper


class UserMapper : Mapper<User, UserModel>() {

    override fun reverseMap(value: UserModel) = User(
        email = value.email,
        password = value.password,
        isLogged = value.isLogged
    )

    override fun map(value: User) = UserModel(
        email = value.email,
        password = value.password,
        isLogged = value.isLogged
    )

}