package paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.shoe_management

import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository.ShoeManagementRepository


class RegisterShoeUseCase(private val shoeManagementRepository: ShoeManagementRepository) {

    suspend operator fun invoke() {
        return shoeManagementRepository.registerShoe()
    }

}