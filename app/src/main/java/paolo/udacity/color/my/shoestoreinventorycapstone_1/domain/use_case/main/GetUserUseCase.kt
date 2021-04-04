package paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.main

import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.entity.User
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository.AuthenticationRepository

class GetUserUseCase(private val authenticationRepository: AuthenticationRepository) {

    suspend operator fun invoke() : User {
        return authenticationRepository.getLoggedUser()
    }

}