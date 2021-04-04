package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import org.threeten.bp.OffsetDateTime
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.models.UserEntity
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.provider.DateTimeProvider
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.provider.di.NAMED_OFFSET_PROVIDER


@Dao
abstract class UserDao : BaseDao<UserEntity>, KoinComponent {

    private val dateTimeProvider: DateTimeProvider<OffsetDateTime> by inject(named(NAMED_OFFSET_PROVIDER))

    @Transaction
    open suspend fun insertOrUpdate(entity: UserEntity) {
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

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    abstract suspend fun findById(id: Long): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE email = :email AND password = :password LIMIT 1")
    abstract suspend fun findByEmailAndPassword(email: String, password: String): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE is_logged = :isLogged")
    abstract suspend fun findLogged(isLogged: Boolean): UserEntity?

    @Query("UPDATE UserEntity SET is_logged = 1")
    abstract suspend fun logout()

    @Query("DELETE FROM UserEntity")
    abstract suspend fun cleanTable()

}