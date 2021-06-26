package com.edw.roomdatabase

import android.app.Application
import android.content.Context
import com.edw.roomdatabase.db.CityManager
import com.edw.roomdatabase.db.gameroledb.GameRoleManager
import kotlin.properties.Delegates

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
class App : Application() {

      private var dbManager: GameRoleManager? = null
      private var cityManager: CityManager? = null


      companion object {
            private var mC by Delegates.notNull<Context>()
            fun appContext(): Context = mC
      }


      override fun onCreate() {
            super.onCreate()
            mC = this
            dbManager = GameRoleManager.getInstance()
            cityManager = CityManager.getInstance(this)

      }

      override fun onTerminate() {
            super.onTerminate()
            dbManager?.apply {
                  close()
            }
            dbManager = null

            cityManager?.apply {
                  close()
            }
            cityManager = null
      }

}