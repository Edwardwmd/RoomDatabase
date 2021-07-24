package com.edw.roomdatabase.db.livedata

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
 * Description:    数据库操作仓库
 ****************************************************************************************************
 */
class DataBaseRepository{

    private object SingleTon {
        val HOLDER = DataBaseRepository()
    }

    companion object {
        private  var dao: BookDao?=null


        fun getInstance(): DataBaseRepository {
            dao = BookDataBase.getInstance().initBookDao()
            return SingleTon.HOLDER
        }

    }

    /**
     * 查询所有结果
     */
   suspend fun getQueryAllData(): LiveData<MutableList<Book>>?{
       return withContext(Dispatchers.IO) {
         dao?.queryAll()
       }
   }

    /**
     * 删除某一个或多个Book
     */
    suspend fun deleteBook(vararg book: Book) {
        withContext(Dispatchers.IO) {
            dao?.deleteBooks(*book)
        }
    }

    /**
     *添加某一个或多个Book
     */
    suspend fun addBook(vararg book: Book) {
        withContext(Dispatchers.IO) {
            dao?.addBooks(*book)
        }
    }

    /**
     * 删除所有数据
     */
    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            dao?.deleteAll()
        }
    }

}