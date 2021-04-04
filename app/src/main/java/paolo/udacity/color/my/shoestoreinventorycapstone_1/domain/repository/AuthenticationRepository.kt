package paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository

import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.entity.User


interface AuthenticationRepository {

    suspend fun login(user: User)
    suspend fun createAccount(user: User)
    suspend fun checkIfLoggedIn() : Boolean
    suspend fun getLoggedUser(): User
    suspend fun logout()

}