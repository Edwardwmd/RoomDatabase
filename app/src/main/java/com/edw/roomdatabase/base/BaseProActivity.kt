package com.edw.roomdatabase.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/*****************************************************************************************************
 * Project Name:    RoomDatabase
 *
 * Date:            2021-07-23
 *
 * Author:         EdwardWMD
 *
 * Github:          https://github.com/Edwardwmd
 *
 * Blog:            https://edwardwmd.github.io/
 *
 * Description:    使用ViewModel和DataBinding的Activity基类
 ****************************************************************************************************
 */
abstract class BaseProActivity<DB : ViewDataBinding, VM : ViewModel> : AppCompatActivity(),
    CoroutineScope by MainScope() {
    protected lateinit var bind: DB
    protected lateinit var vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, initLayoutRes())
        vm = ViewModelProvider(this)[requestDataViewModel()]
        initData()

    }

    abstract fun initData()

    @LayoutRes
    abstract fun initLayoutRes(): Int

    abstract fun requestDataViewModel(): Class<VM>



    override fun onDestroy() {
        super.onDestroy()
        bind.unbind()
        cancel()
    }
}