package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.models

import androidx.room.*
import org.threeten.bp.OffsetDateTime


@Entity(
    indices = [
        Index(value = [UserEntity.FIELD_ID])
    ]
)
data class UserEntity (
    @ColumnInfo(name = FIELD_ID)
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = FIELD_EMAIL)
    var email: String,
    @ColumnInfo(name = FIELD_PASSWORD)
    var password: String,
    @ColumnInfo(name = FIELD_IS_LOGGED)
    var isLogged: Boolean,
    @ColumnInfo(name = FIELD_CREATED_AT)
    var createdAt: OffsetDateTime?,
    @ColumnInfo(name = FIELD_UPDATED_AT)
    var updatedAt: OffsetDateTime? = createdAt
) {

    companion object {
        internal const val FIELD_ID = "id"
        private const val FIELD_EMAIL = "email"
        private const val FIELD_PASSWORD = "password"
        private const val FIELD_IS_LOGGED = "is_logged"
        private const val FIELD_CREATED_AT = "created_at"
        private const val FIELD_UPDATED_AT = "updated_at"
    }

}