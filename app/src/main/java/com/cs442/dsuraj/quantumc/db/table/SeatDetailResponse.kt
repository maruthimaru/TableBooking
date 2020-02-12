package com.cs442.dsuraj.quantumc.db.table

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SeatDetailResponse {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("data")
    @Expose
    var data: List<SeatDetailTable>? = null

}