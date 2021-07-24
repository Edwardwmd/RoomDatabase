package com.edw.roomdatabase.db.livedata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
 * Description:    Book实体类
 ****************************************************************************************************
 */
@Entity(tableName = "SuperBook")
data class Book(
    @ColumnInfo(name = "name")
    var bookName: String? = null,

    @ColumnInfo(name = "desc")
    var desc: String? = null,

    @PrimaryKey
    var id: Long = System.nanoTime()
)