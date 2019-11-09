package buu.informatics.s59160141.calculateelectricitybill.history

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import buu.informatics.s59160141.calculateelectricitybill.database.History
import buu.informatics.s59160141.calculateelectricitybill.database.HistoryDatabaseDao
import kotlinx.coroutines.*

class HistoryViewModel(
    val database: HistoryDatabaseDao,
    application: Application) : AndroidViewModel(application){

    var history = MutableLiveData<List<History?>>()

    fun getData(){
        GlobalScope.launch {

            var result = database.getHistoryAll()
//            for (i in result){
//                Log.i("test", i.unitData +" " +i.priceData)
//            }
            withContext(Dispatchers.Main){
                history.value = result
            }

        }
    }

}