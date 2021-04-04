package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update


interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg obj: T)

    @Insert
    suspend fun delete(vararg obj: T)

}