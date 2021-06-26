package com.edw.roomdatabase.utils

import android.content.Context
import android.widget.Toast
import com.edw.roomdatabase.App

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
object UIUtils {

      fun toast(msg: String) {
            Toast.makeText(App.appContext(), msg, Toast.LENGTH_SHORT).show()
      }

      fun dp2Px(mC:Context,dp:Int):Int{
            val scaleRatio = mC.resources.displayMetrics.density
            return (dp*scaleRatio+0.5f).toInt()
      }
}