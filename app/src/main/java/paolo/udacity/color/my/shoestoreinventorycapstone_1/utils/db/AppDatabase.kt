package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.converter.OffsetDateTimeConverter
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.dao.ShoeDao
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.dao.UserDao
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.models.ShoeEntity
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.models.UserEntity


@Database(
    entities = [
        ShoeEntity::class,
        UserEntity::class
    ],
    version = AppDatabase.VERSION,
    exportSchema = false
)
@TypeConverters(OffsetDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shoeDao(): ShoeDao
    abstract fun userDao(): UserDao

    companion object {
        const val VERSION = 1
        const val NAME = "db"
    }

}