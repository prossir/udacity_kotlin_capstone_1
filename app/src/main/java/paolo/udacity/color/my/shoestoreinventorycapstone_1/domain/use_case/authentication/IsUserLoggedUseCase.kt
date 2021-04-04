package paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.authentication

import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository.AuthenticationRepository


class IsUserLoggedUseCase(private val authenticationRepository: AuthenticationRepository) {

    suspend operator fun invoke(): Boolean {
        return authenticationRepository.checkIfLoggedIn()
    }

}