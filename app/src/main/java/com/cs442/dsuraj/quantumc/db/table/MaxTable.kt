package com.cs442.dsuraj.quantumc.db.table

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MaxTable {
    @SerializedName("max(BOOKINGID)")
    @Expose
    var maxBOOKINGID: String? = null

}