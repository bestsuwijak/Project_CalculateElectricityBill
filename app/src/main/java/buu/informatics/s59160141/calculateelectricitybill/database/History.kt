package buu.informatics.s59160141.calculateelectricitybill.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class History(

    @PrimaryKey(autoGenerate = true)
    var historyId: Long? = 0L,

    @ColumnInfo(name = "unit_data")
    val unitData: String,

    @ColumnInfo(name = "price_data")
    var priceData: String
)