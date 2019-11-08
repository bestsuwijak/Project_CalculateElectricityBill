package buu.informatics.s59160141.calculateelectricitybill.calculate

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.informatics.s59160141.calculateelectricitybill.ElectricBill
import buu.informatics.s59160141.calculateelectricitybill.database.HistoryDatabaseDao

@Suppress("UNCHECKED_CAST")
class CalculateViewModelFactory(
    private val dataSource: HistoryDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculateViewModel::class.java)) {
            return CalculateViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}