package com.edw.roomdatabase.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

/*****************************************************************************************************
 * Project Name:    RoomDatabase
 *
 * Date:            2021-07-24
 *
 * Author:         EdwardWMD
 *
 * Github:          https://github.com/Edwardwmd
 *
 * Blog:            https://edwardwmd.github.io/
 *
 * Description:    弹窗
 ****************************************************************************************************
 */
object DialogUtils {
    private var ls: PositiveButton? = null

    fun dialogCreate(mC: Context,msg:String?):DialogUtils {
        val builder = AlertDialog
            .Builder(mC)
            .setTitle("提示")
            .setMessage("您确定要删除${msg}吗?")
            .setCancelable(true)
        builder.setPositiveButton("确定") { dialog, which ->
            ls?.also {
                it.confirmResult()
            }
            dialog.cancel()
        }

        builder.setNegativeButton("取消") { dialog, which ->
            dialog.cancel()
        }

        builder.show()
        return this
    }

    interface PositiveButton {
        fun confirmResult()
    }

    fun setPositiveButton(ls: PositiveButton) {
        this.ls = ls
    }
}