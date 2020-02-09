package com.cs442.dsuraj.quantumc.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cs442.dsuraj.quantumc.db.dao.MovieBookedDao
import com.cs442.dsuraj.quantumc.db.dao.UserInfoDao
import com.cs442.dsuraj.quantumc.db.table.Movies
import com.cs442.dsuraj.quantumc.db.table.MoviesBooked
import com.cs442.dsuraj.quantumc.db.table.UserInfo

@Database(
        entities = [Movies::class,MoviesBooked::class,UserInfo::class], version = 7, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {

    abstract fun movieBooking() :MovieBookedDao
    abstract fun userInfo():UserInfoDao
            companion object {
        private var INSTANCE: AppDatabase? = null

        internal fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {

                        INSTANCE = Room.databaseBuilder(
                                context.applicationContext,
                                AppDatabase::class.java, "TableBooking.db"
                        )
                                .allowMainThreadQueries()
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

}
