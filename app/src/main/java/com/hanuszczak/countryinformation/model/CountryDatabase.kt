package com.hanuszczak.countryinformation.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hanuszczak.countryinformation.model.dao.CountryDao
import com.hanuszczak.countryinformation.model.entity.CountryEntity

@Database(entities = [CountryEntity::class], version = 1, exportSchema = false)
abstract class CountryDatabase : RoomDatabase() {
    abstract val countryDao: CountryDao

    companion object {
        @Volatile
        private var INSTANCE: CountryDatabase? = null

        fun getInstance(context: Context): CountryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CountryDatabase::class.java,
                        "country_db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}