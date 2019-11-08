package buu.informatics.s59160141.calculateelectricitybill.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HistoryDatabaseDao {
    @Insert
    fun insert(history: History)

    @Update
    fun update(history: History)

    @Query("SELECT * from history_table WHERE historyId = :key")
    fun get(key: Long): History?

    @Query("DELETE FROM history_table")
    fun clear()

//    @Query("SELECT * FROM history_table ORDER BY historyId DESC")
//    fun getHistory(): LiveData<List<History>>

    @Query("SELECT * FROM history_table ORDER BY historyId DESC LIMIT 1")
    fun getLastHistory(): History?

    @Query("SELECT * FROM history_table")
    fun getHistoryAll(): List<History>
}