package com.edw.roomdatabase.db.gameroledb

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

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
class GameRoleManager {

      companion object {

            private const val TAG = "GameRoleManager"
            private var gameRoleDao: GameRoleDao? = null
            private var db: GameRoleDB? = null

            @Volatile
            private var instance: GameRoleManager? = null
            fun getInstance() = instance ?: synchronized(GameRoleManager::class.java) {
                  instance ?: initManager().also { instance = it }
            }

            private fun initManager(): GameRoleManager {
                  db = GameRoleDB.getInstance()
                  gameRoleDao = db?.initGameRoleDao()
                  return GameRoleManager()
            }

      }


      /**
       * 插入单条数据
       */
      @Synchronized
      fun insertRole(role: GameRole): Completable? {
            return gameRoleDao?.run {
                  insertRole(role)
                        .subscribeOn(Schedulers.io())
            }

      }

      /**
       * 插入多条数据
       */
      @Synchronized
      fun insertRoles(roles: MutableList<GameRole>): Completable? {
            return gameRoleDao?.run {
                  insertRoles(roles)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }

      }

      /**
       * 删除所有数据
       */
      @Synchronized
      fun deleteAll(): Completable? {
            return gameRoleDao?.run {
                  deleteAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
      }

      /**
       * 根据角色名来删除数据
       */
      @Synchronized
      fun deleteByName(name: String): Completable? {
            return gameRoleDao?.run {
                  deleteByName(name)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())

            }

      }

      /**
       * 查询所有数据
       */
      fun queryAllData(): Single<MutableList<GameRole>>? {
            return gameRoleDao?.run {
                  queryAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
      }

      /**
       * 根据性别查询结果
       */
      fun queryByGender(gender: String): Single<MutableList<GameRole>>? {
            return gameRoleDao?.run {
                  queryByGender(gender)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
      }

      /**
       * 关闭数据库，断开数据库连接
       */
      fun close() {
            db?.apply {
                  close()
                  db = null
            }
            gameRoleDao?.apply {
                  gameRoleDao = null
            }
      }
}