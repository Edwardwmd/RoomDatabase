package com.edw.roomdatabase.db.livedata

import androidx.lifecycle.LiveData
import androidx.room.*

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
 * Description:    Book增删改的操作类
 ****************************************************************************************************
 */
@Dao
interface BookDao {
    /**
     * 查询结果
     */
    @Query("SELECT * FROM superbook")
    fun queryAll(): LiveData<MutableList<Book>>

    /**
     * 添加一个或者多个结果
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBooks(vararg book: Book)

    /**
     * 删除一个或者多个结果
     */
    @Delete
    suspend fun deleteBooks(vararg book: Book)

    /**
     * 删除所有数据
     */
    @Query("DELETE FROM superbook")
    suspend fun deleteAll()


}