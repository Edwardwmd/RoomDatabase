package com.edw.roomdatabase.db.livedata

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edw.roomdatabase.App
import java.util.concurrent.TimeUnit

/*****************************************************************************************************
 * Project Name:    RoomDatabase
 *
 * Date:            2021-07-23
 *
 * Author:         EdwardWMD
 *
 * Github:          https://github.com/Edwardwmd
 *
 * Blog:            https://edwardwmd.github.io/
 *
 * Description:    Book数据库
 ****************************************************************************************************
 */
@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookDataBase : RoomDatabase() {
    companion object{
        private const val DB_NAME="bookLib"
        private var INSTANCE:BookDataBase?=null
        fun getInstance()= INSTANCE?: synchronized(this){
            INSTANCE?:initBookDataBase().also{ INSTANCE=it}
        }

        private fun initBookDataBase():BookDataBase {

            return Room
                .databaseBuilder(App.appContext(),BookDataBase::class.java, DB_NAME)
                .setAutoCloseTimeout(2,TimeUnit.MINUTES)
                .build()
        }
    }

    abstract fun initBookDao():BookDao
}