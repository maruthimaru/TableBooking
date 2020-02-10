package com.cs442.dsuraj.quantumc.db.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movies")
class Movies {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("MOVIE_ID")
    var MOVIE_ID = 0
    @SerializedName("NAME")
    var NAME: String? = null
    @SerializedName("INFORMATION")
    var INFORMATION: String? = null
    @SerializedName("RATINGS")
    var RATINGS: String? = null

    constructor(MOVIE_ID: Int, NAME: String?, INFORMATION: String?, RATINGS: String?) {
        this.MOVIE_ID = MOVIE_ID
        this.NAME = NAME
        this.INFORMATION = INFORMATION
        this.RATINGS = RATINGS
    }


}