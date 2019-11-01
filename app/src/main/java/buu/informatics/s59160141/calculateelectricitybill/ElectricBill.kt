package buu.informatics.s59160141.calculateelectricitybill

data class ElectricBill(
    var unit:String = "", var res150:String = "", var res250:String = "",
    var res400:String = "", var serviceCharge:String = "",
    var ft:String = "", var vat:String = "", var sum: String = ""
)