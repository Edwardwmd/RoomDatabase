package com.edw.roomdatabase.base

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.core.Observable.create
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import java.util.concurrent.TimeUnit

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
abstract class BaseAdapter<T, VB : ViewDataBinding> : RecyclerView.Adapter<BaseViewHolder>() {

      companion object {

            private const val TAG = "BaseAdapter"
      }

      private val dataList by lazy { mutableListOf<T>() }
      private var ls: ItemClickListener<T>? = null
      private var lls: ItemLongClickListener<T>? = null

      fun setData(data: MutableList<T>) {
            if (dataList.size > 0) dataList.clear()
            dataList.addAll(data)
            notifyDataSetChanged()
      }

      fun removeAll() {
            if (dataList.size > 0) dataList.clear()
            notifyDataSetChanged()
      }

      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val binding = DataBindingUtil.inflate<VB>(
                  LayoutInflater.from(parent.context),
                  initLayoutRes(),
                  parent,
                  false
            )
            return BaseViewHolder(binding.root)
      }


      override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            val binding = DataBindingUtil.getBinding<VB>(holder.itemView)
            onDataBindViewHolder(holder, binding, dataList[position], position)
            create(ObservableOnSubscribe<View> { e ->
                  holder.itemView.setOnClickListener {
                        e.onNext(it)
                  }
            })
                  .throttleFirst(500, TimeUnit.MILLISECONDS)
                  .subscribe({
                        ls?.apply {
                              itemClick(holder.itemView,dataList[position], position)
                        }
                  }, {
                        Log.e(TAG, "防抖动点击错误----》${it.message} ")
                  })


            create(ObservableOnSubscribe<View> { e ->
                  holder.itemView.setOnLongClickListener {
                        e.onNext(it)
                        true
                  }
            })
                  .throttleFirst(500, TimeUnit.MILLISECONDS)
                  .subscribe({
                        lls?.apply {
                              itemLongClick(holder.itemView,dataList[position], position)
                        }
                  }, {
                        Log.e(TAG, "长按防抖动点击错误----》${it.message} ")
                  })
      }


      override fun getItemCount(): Int {
            return dataList.size
      }

      @LayoutRes
      abstract fun initLayoutRes(): Int

      abstract fun onDataBindViewHolder(holder: BaseViewHolder, binding: VB?, element: T, position: Int)

      interface ItemClickListener<T> {

            fun itemClick(v: View,element:T,position: Int)
      }

      interface ItemLongClickListener<T> {

            fun itemLongClick(v: View,element:T, position: Int)
      }

      fun setItemClickListener(ls: ItemClickListener<T>) {
            this.ls = ls
      }

      fun setItemLongClickListener(lls: ItemLongClickListener<T>) {
            this.lls = lls
      }


}