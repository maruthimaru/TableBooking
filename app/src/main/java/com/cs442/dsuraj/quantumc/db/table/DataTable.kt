package com.cs442.dsuraj.quantumc.db.table

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataTable {
    @SerializedName("NAME")
    @Expose
    var nAME: String? = null
    @SerializedName("THEATRES")
    @Expose
    var tHEATRES: String? = null
    @SerializedName("PRICE")
    @Expose
    var pRICE: String? = null
    @SerializedName("SHOW_DATE")
    @Expose
    var sHOWDATE: String? = null
    @SerializedName("TIMINGS")
    @Expose
    var tIMINGS: String? = null
    @SerializedName("EMAIL")
    @Expose
    var eMAIL: String? = null
    @SerializedName("PHONE")
    @Expose
    var pHONE: String? = null
    @SerializedName("SEATS_NO")
    @Expose
    var sEATSNO: String? = null

}