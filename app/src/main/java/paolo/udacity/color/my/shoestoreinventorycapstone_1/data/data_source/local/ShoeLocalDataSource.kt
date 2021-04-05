package paolo.udacity.color.my.shoestoreinventorycapstone_1.data.data_source.local

import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.models.ShoeEntity
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.provider.DaoProvider


class ShoeLocalDataSource(
        daoProvider: DaoProvider
) {

    private val shoeDao = daoProvider.getShoeDao()

    suspend fun insertShoe(entity: ShoeEntity) {
        shoeDao.insertOrUpdate(entity)
    }

    suspend fun findShoes(userId: Long): List<ShoeEntity> {
        return shoeDao.findAllByUserId(userId)
    }

}