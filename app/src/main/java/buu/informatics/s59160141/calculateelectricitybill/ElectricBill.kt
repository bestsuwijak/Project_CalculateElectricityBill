package buu.informatics.s59160141.calculateelectricitybill

data class ElectricBill(
    var unit:String = "", var res150:String = "0", var res250:String = "0",
    var res400:String = "0", var serviceCharge:String = "",
    var ft:String = "", var vat:String = "", var sum: String = ""
)