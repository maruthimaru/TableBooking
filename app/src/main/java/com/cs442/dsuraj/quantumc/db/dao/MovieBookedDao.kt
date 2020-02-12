package com.cs442.dsuraj.quantumc.db.dao

import android.database.Cursor
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

    //lastDetails
    @Query("SELECT t1.NAME,t2.THEATRES,t2.PRICE,t2.SHOW_DATE,t2.TIMINGS,t2.EMAIL,t2.PHONE,t2.SEATS_NO FROM Movies_Booked t2,Movies t1 WHERE  t1.MOVIE_ID=t2.MOVIE_ID AND t2.BOOKINGID=:Booking_ID")
    fun getData(Booking_ID:String):Cursor

    @Query("select SEATS_NO from Movies_Booked where MOVIE_ID = :movie_id and THEATRES = :Theatre and TIMINGS = :time AND SHOW_DATE = :date")
    fun getseats(Theatre: String, date: String, time: String, movie_id: Int):Cursor

    @Query("Select max(BOOKINGID) from Movies_Booked")
    fun getMax():Int
}