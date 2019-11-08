package buu.informatics.s59160141.calculateelectricitybill.calculate

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import buu.informatics.s59160141.calculateelectricitybill.ElectricBill
import buu.informatics.s59160141.calculateelectricitybill.database.History
import buu.informatics.s59160141.calculateelectricitybill.database.HistoryDatabaseDao
import kotlinx.coroutines.*

class CalculateViewModel(
    val database: HistoryDatabaseDao,
    application: Application) : AndroidViewModel(application){

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val history = database.getHistory()
    private var lastHistory = MutableLiveData<History?>()

    private val _unit = MutableLiveData<String>()
    val unit: LiveData<String>
        get() = _unit

    var _price = MutableLiveData<String>()
//    var price: LiveData<String>
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

    fun insertEb(electricBill: ElectricBill){
        GlobalScope.launch {
            //database.clear()
            insert(History(null, electricBill.unit, electricBill.sum))
            var re = database.getLastHistory()
            withContext(Dispatchers.Main){
                _price.value = re?.priceData
                //Log.i("xxxx",re?.priceData)

            }
        }

    }



    private suspend fun insert(newHistory: History){
        withContext(Dispatchers.IO) {
            database.insert(newHistory)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _eventCalculate = MutableLiveData<Boolean>()
    val eventCalculate: LiveData<Boolean>
        get() = _eventCalculate

    fun onBtnCalculate() {
        _eventCalculate.value = true
    }
    fun onBtnCalculateComplete() {
        _eventCalculate.value = false
    }

}