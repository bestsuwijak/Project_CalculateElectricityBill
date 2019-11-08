package buu.informatics.s59160141.calculateelectricitybill.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase(){


    abstract val historyDatabaseDao: HistoryDatabaseDao

    companion object {

        private var INSTANCE: HistoryDatabase? = null

        fun getInstance(context: Context): HistoryDatabase {

            synchronized(this) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java,
                        "history_database"
                    )

                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE as HistoryDatabase
            }
        }
    }
}