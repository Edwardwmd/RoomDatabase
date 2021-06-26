package com.edw.roomdatabase.db.citydb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*****************************************************************************************************
 * Project Name:    RoomDatabase
 *
 * Date:            2021-06-25
 *
 * Author:         EdwardWMD
 *
 * Github:          https://github.com/Edwardwmd
 *
 * Blog:            https://edwardwmd.github.io/
 *
 * Description:    ToDo
 ****************************************************************************************************
 */
@Database(entities = [City::class], version = 1)
abstract class CityDB : RoomDatabase() {

      abstract fun initCityDao(): CityDao

      companion object {

            private const val DB_NAME = "china_all_cities.db"

            @Volatile
            private var INSTANCE: CityDB? = null
            fun getInstance(mC: Context) = INSTANCE ?: synchronized(this) {
                  INSTANCE ?: initCityDB(mC).also { INSTANCE = it }
            }

            private fun initCityDB(mC: Context): CityDB {

                  return Room
                        .databaseBuilder(mC.applicationContext, CityDB::class.java, DB_NAME)
                            //这个文件位置是assets/database/china_cities_v2.db
                        .createFromAsset("database/china_cities_v2.db")
                        .build()
            }

      }


}