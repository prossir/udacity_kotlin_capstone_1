package paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.main

import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository.AuthenticationRepository


class LogOutUseCase(private val authenticationRepository: AuthenticationRepository) {

    suspend operator fun invoke() {
        return authenticationRepository.logout()
    }

}