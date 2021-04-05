package paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository

import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.entity.Shoe


interface ShoeManagementRepository {

    suspend fun getShoes() : List<Shoe>
    suspend fun registerShoe(shoe: Shoe)

}