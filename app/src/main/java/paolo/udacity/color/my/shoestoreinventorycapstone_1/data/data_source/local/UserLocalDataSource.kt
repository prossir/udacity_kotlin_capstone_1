package paolo.udacity.color.my.shoestoreinventorycapstone_1.data.data_source.local

import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.models.UserEntity
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.provider.DaoProvider


class UserLocalDataSource(
    daoProvider: DaoProvider
) {

    private val userDao = daoProvider.getUserDao()

    suspend fun insertUser(entity: UserEntity) {
        userDao.insertOrUpdate(entity)
    }

    suspend fun login(email: String, password: String) {
        val user = userDao.findByEmailAndPassword(email, password)
        user?.let {
            it.isLogged = true
            userDao.insertOrUpdate(it)
        } ?: kotlin.run {
            throw Exception("No user with this credentials found.")
        }
    }

    suspend fun findLoggedUser() : UserEntity? {
        return userDao.findLogged(true)
    }

    suspend fun logout() {
        userDao.logout()
    }

}