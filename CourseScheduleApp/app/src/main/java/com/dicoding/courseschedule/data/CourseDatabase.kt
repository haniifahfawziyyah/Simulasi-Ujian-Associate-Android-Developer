package com.dicoding.courseschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

//TODO 3 : Define room database class
@Database(
    entities = [Course::class],
    version = 1,
    exportSchema = false)
abstract class CourseDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao

    companion object {

        @Volatile
        private var instance: CourseDatabase? = null

        fun getInstance(context: Context): CourseDatabase {
            return synchronized(this){
                instance ?: Room.databaseBuilder(context, CourseDatabase::class.java, "courses.db")

                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
                            db.execSQL(
                                "" +
                                        "INSERT OR REPLACE INTO room_master_table (id,identity_hash) " +
                                        "VALUES(42, '7e795ce7c60545014232ad725936b652')"
                            )
                        }
                    })
                    .build()
            }
        }

    }
}
