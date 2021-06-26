package com.edw.roomdatabase.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

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
abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

      protected var binding: VB? = null

      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, initLayoutRes())
            initData(savedInstanceState)

      }


      @LayoutRes
      abstract fun initLayoutRes(): Int

      abstract fun initData(savedInstanceState: Bundle?)

      override fun onDestroy() {
            super.onDestroy()
            checkNotNull(binding)
            binding?.apply {
                  unbind()
                  binding = null
            }
      }


}