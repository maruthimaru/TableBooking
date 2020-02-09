package com.cs442.dsuraj.quantumc.db.table

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "User_Info")
class UserInfo {
    @PrimaryKey
    @NonNull
    @SerializedName("USER_ID")
    var USER_ID:String?=""
    @SerializedName("NAME")
    var NAME: String? = null
    @SerializedName("EMAIL")
    var EMAIL: String? = null

    constructor(USER_ID: String?, NAME: String?, EMAIL: String?) {
        this.USER_ID = USER_ID
        this.NAME = NAME
        this.EMAIL = EMAIL
    }
}