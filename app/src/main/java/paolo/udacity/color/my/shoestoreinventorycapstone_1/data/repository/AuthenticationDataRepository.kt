package paolo.udacity.color.my.shoestoreinventorycapstone_1.data.repository

import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.data_source.local.UserLocalDataSource
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.mapper.UserLocalMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.entity.User
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository.AuthenticationRepository


class AuthenticationDataRepository(
    private val localDataSource: UserLocalDataSource,
    private val localMapper: UserLocalMapper
) : AuthenticationRepository {

    override suspend fun login(user: User) {
        localDataSource.login(user.email, user.password)
    }

    override suspend fun createAccount(user: User) {
        localDataSource.insertUser(localMapper.map(user))
    }

    override suspend fun checkIfLoggedIn(): Boolean {
        return localDataSource.findLoggedUser() != null
    }

    override suspend fun getLoggedUser(): User {
        return localMapper.reverseMap(localDataSource.findLoggedUser()!!)
    }

    override suspend fun logout() {
        localDataSource.logout()
    }

}