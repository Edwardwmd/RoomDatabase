package com.edw.roomdatabase.db.citydb

import androidx.room.Dao
import androidx.room.Query
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
 * Description:    定义数据库操作数据增、删、改、查的一个类
 ****************************************************************************************************
 */
@Dao
interface CityDao {

      /**
       * 根据ID查询城市
       */
      @Query("SELECT * FROM cities WHERE id=:id")
      fun queryById(id: Int): Single<MutableList<City>>

      /**
       * 根据城市名称查询城市
       */
      @Query("SELECT * FROM cities WHERE c_name=:cityName")
      fun queryByCityName(cityName: String): Single<MutableList<City>>

      /**
       * 根据省会查询省下管辖的城市
       */
      @Query("SELECT * FROM cities WHERE c_province=:cityProvince")
      fun queryByCityProvince(cityProvince: String): Single<MutableList<City>>

      /**
       * 查询所有程序(升序排列)
       */
      @Query("SELECT * FROM cities ORDER BY c_pinyin ASC")
      fun queryAllByAsc(): Single<MutableList<City>>

      /**
       * 查询所有城市(降序排列)
       */
      @Query("SELECT * FROM cities ORDER BY c_pinyin DESC")
      fun queryAllByDesc(): Single<MutableList<City>>

      /**
       * select * from cities where c_pinyin like % keyWord % or c_name like % keyWord % or c_province like % keyWord %
       * 根据数据关键字进行模糊查询,||表示+的意思
       *   '%a%’表示只要该字段含有a字
       *   ‘a%’表示要模糊的字必须处于该字段的首位
       *   ‘%a’表示要模糊的字必须处于该字段末位
       *
       */
      @Query("SELECT * FROM cities WHERE c_pinyin LIKE :keyWord || '%' OR c_name LIKE '%' || :keyWord || '%' OR c_province LIKE '%' || :keyWord || '%' " )
      fun queryByKeyWord(keyWord:String): Single<MutableList<City>>
}