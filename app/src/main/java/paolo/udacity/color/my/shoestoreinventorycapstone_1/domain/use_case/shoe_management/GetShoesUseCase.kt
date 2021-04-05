package paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.shoe_management

import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.entity.Shoe
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository.ShoeManagementRepository

class GetShoesUseCase(private val shoeManagementRepository: ShoeManagementRepository) {

    suspend operator fun invoke() : List<Shoe> {
        return shoeManagementRepository.getShoes()
    }

}