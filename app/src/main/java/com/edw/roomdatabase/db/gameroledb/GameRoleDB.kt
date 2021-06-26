package com.edw.roomdatabase.db.gameroledb

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edw.roomdatabase.App

/*****************************************************************************************************
 * Project Name:    RoomDatabase
 *
 * Date:            2021-06-24
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
@Database(entities = [GameRole::class], version = 1, exportSchema = false)
abstract class GameRoleDB : RoomDatabase() {

      abstract fun initGameRoleDao(): GameRoleDao

      companion object {
            private const val TAG = "GameRoleDB"

            @Volatile
            private var instance: GameRoleDB? = null
            fun getInstance() = instance ?: synchronized(GameRoleDB::class.java) {
                  instance ?: initDB().also { instance = it }
            }

            private fun initDB(): GameRoleDB {

                  return Room.databaseBuilder(App.appContext(), GameRoleDB::class.java, "GameInfo.db").build()
            }
      }

}