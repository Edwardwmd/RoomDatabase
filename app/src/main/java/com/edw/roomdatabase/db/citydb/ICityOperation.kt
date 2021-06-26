package com.edw.roomdatabase.db.citydb

import io.reactivex.rxjava3.core.Single

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
 * Description:    数据库操作接口
 ****************************************************************************************************
 */
interface ICityOperation {

      fun queryById(id: Int): Single<MutableList<City>>?

      fun queryByCityName(cityName: String): Single<MutableList<City>>?

      fun queryByCityProvince(cityProvince: String): Single<MutableList<City>>?

      fun queryAll(sortType: String): Single<MutableList<City>>?

      fun queryByKeyWord(keyWord:String): Single<MutableList<City>>?
}