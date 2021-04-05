package paolo.udacity.color.my.shoestoreinventorycapstone_1.data.repository

import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.data_source.local.ShoeLocalDataSource
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.data_source.local.UserLocalDataSource
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.mapper.ShoeLocalMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.entity.Shoe
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.repository.ShoeManagementRepository


class ShoeManagementDataRepository(
        private val userLocalDataSource: UserLocalDataSource,
        private val localDataSource: ShoeLocalDataSource,
        private val localMapper: ShoeLocalMapper
) : ShoeManagementRepository {

    override suspend fun getShoes(): List<Shoe> {
        val user = userLocalDataSource.findLoggedUser()!!
        return localMapper.map(localDataSource.findShoes(user.id))
    }

    override suspend fun registerShoe(shoe: Shoe) {
        val user = userLocalDataSource.findLoggedUser()!!
        localMapper.reverseMap(shoe).let {
            it.userId = user.id
            localDataSource.insertShoe(it)
        }
    }

}