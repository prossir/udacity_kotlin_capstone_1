package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime


@Entity(
        indices = [
            Index(value = [ShoeEntity.FIELD_ID])
        ]
)
data class ShoeEntity(
        @ColumnInfo(name = FIELD_ID)
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0L,
        @ColumnInfo(name = FIELD_NAME)
        var name: String,
        @ColumnInfo(name = FIELD_SIZE)
        var size: Double,
        @ColumnInfo(name = FIELD_COMPANY)
        var company: String,
        @ColumnInfo(name = FIELD_DESCRIPTION)
        var description: String,
        @ColumnInfo(name = FIELD_USER_ID)
        var userId: Long,
        @ColumnInfo(name = FIELD_CREATED_AT)
        var createdAt: OffsetDateTime?,
        @ColumnInfo(name = FIELD_UPDATED_AT)
        var updatedAt: OffsetDateTime? = createdAt,
) {

    companion object {
        internal const val FIELD_ID = "id"
        private const val FIELD_NAME = "name"
        private const val FIELD_SIZE = "size"
        private const val FIELD_COMPANY = "company"
        private const val FIELD_DESCRIPTION = "description"
        private const val FIELD_USER_ID = "user_id"
        private const val FIELD_CREATED_AT = "created_at"
        private const val FIELD_UPDATED_AT = "updated_at"
    }

}