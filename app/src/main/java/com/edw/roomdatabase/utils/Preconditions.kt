package com.edw.roomdatabase.utils

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
 * Description:    检测对象是否为空的先决类
 ****************************************************************************************************
 */
class Preconditions {
    companion object {

        fun <T> checkNull(elem: T?) {
            if (elem == null) {
                throw NullPointerException("you inserted Param is null~~~")
            }
        }

        fun <T> checkObject(elem: T?): T {
            if (elem == null) {
                throw NullPointerException("The Parameter Value Is Null,Please Check Your Code Source~~~")
            }
            return elem
        }
    }
}