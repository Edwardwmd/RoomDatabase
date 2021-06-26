package com.edw.roomdatabase.db.gameroledb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

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
@Dao
interface GameRoleDao {

      //插入单个数据
      @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insertRole(role: GameRole): Completable

      //插入多条数据
      @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insertRoles(roles: MutableList<GameRole>): Completable

      //根据角色名称删除数据
      @Query("DELETE FROM game_role_pro WHERE roleName=:name")
      fun deleteByName(name: String): Completable

      //删除所有数据
      @Query("DELETE FROM game_role_pro")
      fun deleteAll(): Completable

      //查询数据
      @Query("SELECT * FROM game_role_pro")
      fun queryAll(): Single<MutableList<GameRole>>

      //根据性别查询数据
      @Query("SELECT * FROM game_role_pro WHERE roleGender=:gender")
      fun queryByGender(gender: String): Single<MutableList<GameRole>>

}