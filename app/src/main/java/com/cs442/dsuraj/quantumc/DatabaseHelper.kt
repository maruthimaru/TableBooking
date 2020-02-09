package com.cs442.dsuraj.quantumc

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Suraj Didwania on 04-11-2016.
 */
class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME1 (NAME VARCHAR, MOVIE_ID INTEGER PRIMARY KEY AUTOINCREMENT,INFORMATION VARCHAR, RATINGS INTEGER)")
        db.execSQL("create table $TABLE_NAME3 (BOOKINGID INTEGER PRIMARY KEY AUTOINCREMENT,MOVIE_ID INTEGER,TIMINGS TIME,THEATRES VARCHAR,EMAIL VARCHAR,PRICE INTEGER,SHOW_DATE DATE, PHONE  VARCHAR,SEATS_NO  VARCHAR,FOREIGN KEY(MOVIE_ID) REFERENCES Movies(MOVIE_ID))")
        db.execSQL("create table $TABLE_NAME4 (USER_ID VARCHAR, NAME VARCHAR,EMAIL VARCHAR )")
        val content = ContentValues()
        db.execSQL("insert into $TABLE_NAME1 (NAME,INFORMATION, RATINGS)VALUES('Round Table','A man caught in the middle of two simultaneous robberies at the same bank desperately tries to protect the teller with whom he secretly in love.','5')")
        db.execSQL("insert into $TABLE_NAME1 (NAME,INFORMATION,RATINGS)VALUES('Meeting Table','Ethan and team take on their most impossible mission yet, eradicating the Syndicate - an International rogue organization as highly skilled as they are, committed to destroying the IMF.','4')")
        db.execSQL("insert into $TABLE_NAME1 (NAME,INFORMATION,RATINGS)VALUES('Premium Table','Earths mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity.','3')")
        db.execSQL("insert into $TABLE_NAME1 (NAME,INFORMATION,RATINGS)VALUES('Celebrity Table','When New York is put under siege by Oscorp, it is up to Spider-Man to save the city he swore to protect as well as his loved ones.','4')")
        // db.execSQL("insert into " + TABLE_NAME4 + " (SEAT,THEATRE,TIMINGS,DATE_CURRENT, MOVIE_ID)" + "VALUES" + "('A1','AMC RIVER EAST 21',''11:55',)");
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME1")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME3")
        onCreate(db)
    }

    //Sushma Database part
    fun insertmoviebooked(id: Int, Seats: String?, theatre: String?, time: String?, date: String?, price: Int, Email: String?, phone: String?): Boolean {
        val db = this.writableDatabase
        val content = ContentValues()
        content.put("Movie_ID", id)
        content.put("TIMINGS", time)
        content.put("THEATRES", theatre)
        content.put("SHOW_DATE", date)
        content.put("PRICE", price)
        content.put("EMAIL", Email)
        content.put("PHONE", phone)
        content.put("SEATS_NO", Seats)
        val result = db.insert(TABLE_NAME3, null, content)
        return if (result == -1L) false else true
    }

    fun insertuser(id: String?, name: String?, email: String?): Boolean {
        val db = this.writableDatabase
        val content = ContentValues()
        content.put("USER_ID", id)
        content.put("NAME", name)
        content.put("EMAIL", email)
        val result = db.insert(TABLE_NAME4, null, content)
        return if (result == -1L) false else true
    }

    //Seat Selection
/*
    public boolean insertseatbooked(int id, String Seats, String theatre, String time, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("Movie_ID",id);
        content.put("TIMINGS",time);
        content.put("THEATRES",theatre);
        content.put("DATE_CURRENT", date);
        content.put("SEATS", Seats);
        long result = db.insert(TABLE_NAME4,null,content);
        if(result == -1) return false; else return true;
    }*/
/*
    public boolean insertdatamovie(String movie_name, String Details, String ratings, String User_review, boolean comingsoon, int seats_available )
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME3);
        onCreate(db);

        ContentValues content = new ContentValues();
        content.put("NAME",movie_name);
        content.put("INFORMATION",Details);
        content.put("RATINGS",ratings);
        long result = db.insert(TABLE_NAME1,null,content);
        if(result == -1) return false; else return true;
    }
    */
/*
    public boolean insertdatatimimgs(int MOVIE_ID,String Theatres, String Timings, int price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("MOVIE_ID",MOVIE_ID);
        content.put("THEATRES",Theatres);
        content.put("TIMINGS",Timings);
        content.put("PRICE",price);
        long result = db.insert(TABLE_NAME2,null,content);
        if(result == -1) return false; else return true;
    }
*/
//Max Booking ID
    fun getmaxbooking(db: SQLiteDatabase): Cursor {
        val cursor: Cursor
        val sql = "Select max(BOOKINGID) from $TABLE_NAME3"
        cursor = db.rawQuery(sql, null)
        return cursor
    }

    fun getData(db: SQLiteDatabase, Booking_ID: String): Cursor {
        val cursor: Cursor
        val sql = "SELECT t1.NAME,t2.THEATRES,t2.PRICE,t2.SHOW_DATE,t2.TIMINGS,t2.EMAIL,t2.PHONE,t2.SEATS_NO FROM Movies_Booked t2,Movies t1 WHERE  t1.MOVIE_ID=t2.MOVIE_ID AND t2.BOOKINGID=? "
        cursor = db.rawQuery(sql, arrayOf(Booking_ID))
        println(Booking_ID)
        return cursor
    }

    //Suraj Part
    fun getseats(db: SQLiteDatabase, Theatre: String, date: String, time: String, movie_id: Int): Cursor? {
        var cursor: Cursor? = null
        print(Theatre + date + time + movie_id)
        val sql = "select SEATS_NO from $TABLE_NAME3 where MOVIE_ID = ? and THEATRES = ? and TIMINGS = ? AND SHOW_DATE = ?"
        cursor = db.rawQuery(sql, arrayOf(Integer.toString(movie_id), Theatre, time, date))
        val md = allDataMovies
        while (md.moveToNext()) {
            print(md)
        }
        print(cursor)
        print(cursor.count)
        return cursor
    }

    val allDataMovies: Cursor
        get() {
            val db = this.writableDatabase
            val sql = "select * from $TABLE_NAME1"
            return db.rawQuery(sql, null)
        }

    /*
    public Cursor getAllDataTimings()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select MovieName,Information,Rating from " + TABLE_NAME2,null);
        return res;

    }*/
    fun getMoviename(db: SQLiteDatabase, movie_id: String): Cursor {
        val cursor: Cursor
        val sql = "select NAME FROM $TABLE_NAME1 where MOVIE_ID = ?"
        cursor = db.rawQuery(sql, arrayOf(movie_id))
        print(movie_id)
        return cursor
    } /*
    public Cursor getAllDataBooked()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select MovieName,Theatre,Timing from " + TABLE_NAME2,null);
        return res;

    }
*/

    companion object {
        const val DATABASE_NAME = "Movie.db"
        const val TABLE_NAME1 = "Movies"
        const val TABLE_NAME3 = "Movies_Booked"
        const val TABLE_NAME4 = "User_Info"
        const val T1_COL_1 = "MovieName"
        const val T1_COL_2 = "Movie_Id"
        const val T1_COL_3 = "Information"
        const val T1_COL_4 = "Ratings"
        const val T1_COL_5 = "User_Reviews"
        const val T1_COL_6 = "ComingSoon"
        const val T1_COL_7 = "Seats_Available"
        const val T2_COL_1 = "Theatres"
        const val T2_COL_2 = "Timings"
        const val T2_COL_3 = "Price"
        const val T2_COL_4 = "Movie_ID"
        const val T3_COL_1 = "Movie_Id"
        const val T3_COL_2 = "Timings"
        const val T3_COL_3 = "Theatres"
        const val T3_COL_4 = "Email"
        const val T3_COL_5 = "Phone"
        const val T3_COL_6 = "Booking_ID"
        const val T3_COL_7 = "Quantity"
    }

    init {
        val db = this.writableDatabase
    }
}