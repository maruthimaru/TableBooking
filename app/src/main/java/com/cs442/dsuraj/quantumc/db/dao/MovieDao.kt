package com.cs442.dsuraj.quantumc.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.cs442.dsuraj.quantumc.db.table.Movies

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    fun insert(arrayList: ArrayList<Movies>)

    @Insert(onConflict = REPLACE)
    fun insertSingleRow(data: Movies)


    @Query("Delete from Movies")
    fun deleteAll()

    //delete single user
    @Query("Delete from Movies where MOVIE_ID=:id")
    fun deleteSingleUser(id:String)

    @Query("Select * from Movies where MOVIE_ID=:mobileNo")
    fun getAllDetails(mobileNo:String):Movies

    //lastDetails
    @Query("Select * from Movies where MOVIE_ID=:mobileNo ORDER BY MOVIE_ID DESC LIMIT 1")
    fun getLastDetails(mobileNo:String):Movies

    @Query("Select * from Movies where MOVIE_ID=:passNo or MOVIE_ID=:passNo")
    fun getPssNoDetails(passNo:String):Movies
}