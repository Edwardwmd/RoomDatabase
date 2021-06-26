package com.edw.roomdatabase.db

import android.content.Context
import androidx.annotation.StringDef
import com.edw.roomdatabase.db.SortType.Companion.ASC
import com.edw.roomdatabase.db.SortType.Companion.DESC
import com.edw.roomdatabase.db.citydb.City
import com.edw.roomdatabase.db.citydb.CityDB
import com.edw.roomdatabase.db.citydb.CityDao
import com.edw.roomdatabase.db.citydb.ICityOperation
import com.edw.roomdatabase.utils.UIUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

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
class CityManager : ICityOperation {

      companion object {

            private var cityDB: CityDB? = null
            private var cityDao: CityDao? = null

            @Volatile
            private var INSTANCE: CityManager? = null
            fun getInstance(mC: Context) = INSTANCE ?: synchronized(this) {
                  INSTANCE ?: initCityManager(mC).also { INSTANCE = it }
            }

            private fun initCityManager(mC: Context): CityManager {
                  cityDB = CityDB.getInstance(mC)
                  cityDao = cityDB!!.initCityDao()
                  return CityManager()
            }

      }

      override fun queryById(id: Int): Single<MutableList<City>>? {
            return cityDao?.run {
                  queryById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
      }

      override fun queryByCityName(cityName: String): Single<MutableList<City>>? {
            return cityDao?.run {
                  queryByCityName(cityName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
      }

      override fun queryByCityProvince(cityProvince: String): Single<MutableList<City>>? {
            return cityDao?.run {
                  queryByCityProvince(cityProvince)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
      }

      override fun queryAll(sortType: String): Single<MutableList<City>>? {
            return when (sortType) {
                  ASC -> {
                        cityDao?.run {
                              queryAllByAsc()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                        }
                  }

                  DESC -> {
                        cityDao?.run {
                              queryAllByDesc()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                        }
                  }

                  else -> {
                        UIUtils.toast("您输入排序类型有误")
                        null
                  }
            }
      }

      override fun queryByKeyWord(keyWord: String): Single<MutableList<City>>? {
            return cityDao?.run {
                  queryByKeyWord(keyWord)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
      }

      /**
       * 关闭数据库连接
       */
      fun close() {
            cityDB?.apply {
                  close()
            }
            cityDao?.apply {
                  cityDao = null
            }
            cityDB = null
      }


}

/**
 * 排序类型
 */
@StringDef(ASC, DESC)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class SortType {

      companion object {

            //升序
            const val ASC = "Sort_ASC"

            //降序
            const val DESC = "Sort_DESC"
      }
}