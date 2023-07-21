package com.example.beste1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OtobusBilet(
    var firmaAdi: String,
    var firmaLogo: String,
    var kalkis: String,
    var nereden: String,
    var nereye: String,
    var sure: String,
    var ucret: Int
) : Parcelable{
    constructor() : this("", "", "", "", "", "", 0)
}