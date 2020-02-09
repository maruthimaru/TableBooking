package com.cs442.dsuraj.quantumc.db.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movies_Booked")
class MoviesBooked {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("BOOKINGID")
    var BOOKINGID = 0
    @SerializedName("MOVIE_ID")
    var MOVIE_ID: String? = null
    @SerializedName("TIMINGS")
    var TIMINGS: String? = null
    @SerializedName("THEATRES")
    var THEATRES: String? = null
    @SerializedName("EMAIL")
    var EMAIL:String?=null
    @SerializedName("PRICE")
    var PRICE: Int= 0
    @SerializedName("SHOW_DATE")
    var SHOW_DATE: String? = null
    @SerializedName("PHONE")
    var PHONE: String? = null
    @SerializedName("SEATS_NO")
    var SEATS_NO: String? = null

    constructor(MOVIE_ID: String?, TIMINGS: String?, THEATRES: String?, EMAIL: String, PRICE: Int, SHOW_DATE: String?, PHONE: String?, SEATS_NO: String?) {
        this.MOVIE_ID = MOVIE_ID
        this.TIMINGS = TIMINGS
        this.THEATRES = THEATRES
        this.EMAIL = EMAIL
        this.PRICE = PRICE
        this.SHOW_DATE = SHOW_DATE
        this.PHONE = PHONE
        this.SEATS_NO = SEATS_NO
    }
}