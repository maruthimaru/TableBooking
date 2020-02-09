package com.cs442.dsuraj.quantumc.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.cs442.dsuraj.quantumc.db.table.UserInfo

@Dao
interface UserInfoDao {

    @Insert(onConflict = REPLACE)
    fun insert(arrayList: ArrayList<UserInfo>)

    @Insert(onConflict = REPLACE)
    fun insertSingleRow(data: UserInfo)


    @Query("Delete from User_Info")
    fun deleteAll()

    //delete single user
    @Query("Delete from User_Info where USER_ID=:id")
    fun deleteSingleUser(id:String)

    @Query("Select * from User_Info where USER_ID=:mobileNo")
    fun getAllDetails(mobileNo:String):UserInfo

    //lastDetails
    @Query("Select * from User_Info where USER_ID=:mobileNo ORDER BY USER_ID DESC LIMIT 1")
    fun getLastDetails(mobileNo:String):UserInfo

    @Query("Select * from User_Info where USER_ID=:passNo or USER_ID=:passNo")
    fun getPssNoDetails(passNo:String):UserInfo
}