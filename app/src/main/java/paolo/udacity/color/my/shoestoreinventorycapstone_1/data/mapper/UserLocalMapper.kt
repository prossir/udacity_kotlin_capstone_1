package paolo.udacity.color.my.shoestoreinventorycapstone_1.data.mapper

import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.entity.User
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.models.UserEntity
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.mapper.Mapper


class UserLocalMapper: Mapper<User, UserEntity>() {

    override fun reverseMap(value: UserEntity) = User(
        email = value.email,
        password = value.password,
        isLogged = value.isLogged
    )

    override fun map(value: User) = UserEntity(
        email = value.email,
        password = value.password,
        isLogged = value.isLogged,
        createdAt = null,
        updatedAt = null
    )

}