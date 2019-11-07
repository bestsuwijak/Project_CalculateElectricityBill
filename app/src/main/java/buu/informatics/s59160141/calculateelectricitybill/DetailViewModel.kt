package buu.informatics.s59160141.calculateelectricitybill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel(unit:String,unit150:String,unit250:String,unit400:String
                      ,serviceCharge:String,ft:String,vat:String,sum:String) : ViewModel() {

    private val _unit = MutableLiveData<String>()
    val unit: LiveData<String>
        get() = _unit

    private val _unit150 = MutableLiveData<String>()
    val unit150: LiveData<String>
        get() = _unit150

    private val _unit250 = MutableLiveData<String>()
    val unit250: LiveData<String>
        get() = _unit250

    private val _unit400 = MutableLiveData<String>()
    val unit400: LiveData<String>
        get() = _unit400

    private val _serviceCharge = MutableLiveData<String>()
    val serviceCharge: LiveData<String>
        get() = _serviceCharge

    private val _baseEletricCharge = MutableLiveData<String>()
    val baseEletricCharge: LiveData<String>
        get() = _baseEletricCharge

    private val _ft = MutableLiveData<String>()
    val ft: LiveData<String>
        get() = _ft

    private val _vat = MutableLiveData<String>()
    val vat: LiveData<String>
        get() = _vat

    private val _sum = MutableLiveData<String>()
    val sum: LiveData<String>
        get() = _sum

    private val _eventOK = MutableLiveData<Boolean>()
    val eventOK: LiveData<Boolean>
        get() = _eventOK

    init{
        _unit.value = unit
        _unit150.value = unit150
        _unit250.value = unit250
        _unit400.value = unit400
        _serviceCharge.value = serviceCharge
        _baseEletricCharge.value = String.format("%1.2f", unit150.toFloat() + unit250.toFloat() + unit400.toFloat() + serviceCharge.toFloat())
        _ft.value = ft
        _vat.value = vat
        _sum.value = sum
    }

    fun onOK() {
        _eventOK.value = true
    }
    fun onOKComplete() {
        _eventOK.value = false
    }

}