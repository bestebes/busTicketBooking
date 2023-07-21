package com.example.beste1.model

import com.example.beste1.view.YolcuModel

data class OtobusSeferlerModel (
    var seferAdi: String,
    var kalkis: String,
    var nereden: String,
    var nereye: String,
    var firmaAd: String,
    var ucret: Int,
    var sure: String,
    var koltuklar: List<YolcuModel>
){
    constructor() : this("", "", "", "", "", 0, "", emptyList())
}