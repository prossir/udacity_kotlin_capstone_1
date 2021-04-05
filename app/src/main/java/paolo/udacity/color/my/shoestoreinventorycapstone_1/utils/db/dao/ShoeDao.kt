package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import org.threeten.bp.OffsetDateTime
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.models.ShoeEntity
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.provider.DateTimeProvider
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.provider.di.NAMED_OFFSET_PROVIDER

@Dao
abstract class ShoeDao : BaseDao<ShoeEntity>, KoinComponent {

    private val dateTimeProvider: DateTimeProvider<OffsetDateTime> by inject(named(NAMED_OFFSET_PROVIDER))

    @Transaction
    open suspend fun insertOrUpdate(entity: ShoeEntity) {
        val now = dateTimeProvider.now()
        val item = findById(entity.id)
        if (item == null) {
            insert(entity.apply {
                createdAt = now
                updatedAt = now
            })
        } else {
            update(entity.apply {
                createdAt = item.createdAt
                updatedAt = now
            })
        }
    }

    @Query("SELECT * FROM ShoeEntity WHERE id = :id")
    abstract suspend fun findById(id: Long): ShoeEntity?

    @Query("SELECT * FROM ShoeEntity WHERE user_id = :userId")
    abstract suspend fun findAllByUserId(userId: Long): List<ShoeEntity>

    @Query("DELETE FROM ShoeEntity")
    abstract suspend fun cleanTable()

}