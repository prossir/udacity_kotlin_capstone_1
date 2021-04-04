package paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.authentication

import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.entity.User
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository.AuthenticationRepository


class AttemptLoginUseCase(private val authenticationRepository: AuthenticationRepository) {

    suspend operator fun invoke(user: User) {
        authenticationRepository.login(user)
    }

}