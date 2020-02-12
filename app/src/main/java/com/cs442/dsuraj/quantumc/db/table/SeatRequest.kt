//-----------------------------------com.cs442.dsuraj.quantumc.db.table.SeatRequest.java-----------------------------------
package com.cs442.dsuraj.quantumc.db.table

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SeatRequest {
    @SerializedName("movie_id")
    @Expose
    var movieId: String? = null
    @SerializedName("Theatre")
    @Expose
    var theatre: String? = null
    @SerializedName("time")
    @Expose
    var time: String? = null
    @SerializedName("data")
    @Expose
    var data: String? = null

    constructor(movieId: String?, theatre: String?, time: String?, data: String?) {
        this.movieId = movieId
        this.theatre = theatre
        this.time = time
        this.data = data
    }
}