package buu.informatics.s59160141.calculateelectricitybill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModelFactory(private val unit: String,
                             private val unit150: String ,
                             private val unit250: String ,
                             private val unit400: String ,
                             private val serviceCharge: String ,
                             private val ft: String ,
                             private val vat: String ,
                             private val sum: String ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(unit,unit150,unit250,unit400,serviceCharge,
                ft,vat,sum) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}