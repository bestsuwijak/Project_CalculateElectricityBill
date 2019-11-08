package buu.informatics.s59160141.calculateelectricitybill.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import buu.informatics.s59160141.calculateelectricitybill.database.History
import buu.informatics.s59160141.calculateelectricitybill.database.HistoryDatabaseDao
import kotlinx.coroutines.*

class HistoryViewModel(
    val database: HistoryDatabaseDao,
    application: Application) : AndroidViewModel(application){

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val history = database.getHistoryAll()
    var lastHistory = MutableLiveData<History?>()

//    private val _unit = MutableLiveData<String>()
//    val unit: LiveData<String>
//        get() = _unit
//
//    private var _price = MutableLiveData<String>()
//    var price: LiveData<String>? = null
//        get() = _price

    init{
        initializeLastHistory()
    }

    private fun initializeLastHistory() {

        uiScope.launch{
           // database.clear()
            lastHistory.value = getLastHistoryFromDatabase()
        }
    }

    private suspend fun getLastHistoryFromDatabase(): History? {
        return withContext(Dispatchers.IO) {
            var last = database.getLastHistory()
            last
        }
    }


}