package com.edw.roomdatabase.db.citydb

import androidx.room.Entity
import androidx.room.PrimaryKey

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
 * Description:    城市Entry类，定义数据库表中的字段
 ****************************************************************************************************
 */
@Entity(tableName = "cities")
data class City(
      //assets中的数据库id如果是AutoIncrement（自增）的话，需要把assets数据库中的AutoIncrement自增去掉，
      //代码中@PrimaryKey也不需要加autoGenerate =true的参数。
      //在Entity中，table名称和table的所有字段必须要跟assets中的字段一致包括字段中如果
      //定义的是NotNull的话，那么Entry中定义的字段也需要与其保持一致。
      @PrimaryKey
      var id: Int = 0,
      var c_name: String ="" ,
      var c_pinyin: String = "",
      var c_code: String ="" ,
      var c_province: String =""
)
