package com.cs442.dsuraj.quantumc.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.cs442.dsuraj.quantumc.db.table.MoviesBooked

@Dao
interface MovieBookedDao {

    @Insert(onConflict = REPLACE)
    fun insert(arrayList: ArrayList<MoviesBooked>)

    @Insert(onConflict = REPLACE)
    fun insertSingleRow(data: MoviesBooked)

    @Query("Delete from Movies_Booked")
    fun deleteAll()

    //delete single user
    @Query("Delete from Movies_Booked where MOVIE_ID=:id")
    fun deleteSingleUser(id:String)

    @Query("Select * from Movies_Booked where MOVIE_ID=:mobileNo")
    fun getAllDetails(mobileNo:String):MoviesBooked

    //lastDetails
    @Query("Select * from Movies_Booked where MOVIE_ID=:mobileNo ORDER BY MOVIE_ID DESC LIMIT 1")
    fun getLastDetails(mobileNo:String):MoviesBooked

    @Query("select SEATS_NO from Movies_Booked where MOVIE_ID = :movie_id and THEATRES = :Theatre and TIMINGS = :time AND SHOW_DATE = :date")
    fun getseats(Theatre: String, date: String, time: String, movie_id: Int):ArrayList<String>

    @Query("Select max(BOOKINGID) from Movies_Booked")
    fun getMax():Int
}