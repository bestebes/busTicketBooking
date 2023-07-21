package com.example.beste1.view

data class YolcuModel (
    var ad: String?,
    var soyad: String?,
    var cinsiyet: String?,
    var secilenKoltukNo: Int?
    ){
    constructor() : this(null, null, null, null)
}
