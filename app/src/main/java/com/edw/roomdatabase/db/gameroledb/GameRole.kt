package com.edw.roomdatabase.db.gameroledb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
 * Description:    游戏角色
 ****************************************************************************************************
 */
@Entity(tableName = "game_role_pro")
data class GameRole(
      //角色名称
      @ColumnInfo(name = "roleName")
      var name: String,
      //角色性别
      @ColumnInfo(name = "roleGender")
      var gender: String,
      //角色技能
      @ColumnInfo(name = "roleSkill")
      var skill: String,
      //角色ID
      @PrimaryKey
      @ColumnInfo
      var id: Long = System.currentTimeMillis()
)
