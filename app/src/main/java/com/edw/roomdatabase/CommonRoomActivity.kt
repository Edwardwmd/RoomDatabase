package com.edw.roomdatabase

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import androidx.recyclerview.widget.LinearLayoutManager
import com.edw.roomdatabase.adapter.AtomDecoration
import com.edw.roomdatabase.adapter.RoomAdapter
import com.edw.roomdatabase.base.BaseActivity
import com.edw.roomdatabase.databinding.ActivityCommonRoomBinding
import com.edw.roomdatabase.db.gameroledb.GameRole
import com.edw.roomdatabase.db.gameroledb.GameRoleManager
import com.edw.roomdatabase.utils.UIUtils

class CommonRoomActivity : BaseActivity<ActivityCommonRoomBinding>() {

      companion object {

            private const val TAG = "CommonRoomActivity"
      }

      private val dbManager by lazy { GameRoleManager.getInstance() }
      private val adapter by lazy { RoomAdapter() }
      private var roleName: String? = null
      private var roleGender: String? = null
      private var roleSkill: String? = null
      private val gendersStr by lazy { arrayListOf("男", "女") }

      override fun initLayoutRes(): Int {
            return R.layout.activity_common_room
      }

      override fun initData(savedInstanceState: Bundle?) {
            binding?.apply {
                  recy.layoutManager = LinearLayoutManager(this@CommonRoomActivity)
                  recy.setHasFixedSize(true)
                  recy.addItemDecoration(AtomDecoration())
                  recy.adapter = adapter

                  etGender.setOnEditorActionListener { v, actionId, event ->
                        if (actionId == KeyEvent.KEYCODE_CALL) {
                              roleGender = etGender.text.toString()
                        }
                        true
                  }

                  etRole.setOnEditorActionListener { v, actionId, event ->
                        if (actionId == KeyEvent.KEYCODE_CALL) {
                              roleName = etRole.text.toString()
                        }
                        true
                  }

                  etSkill.setOnEditorActionListener { v, actionId, event ->
                        if (actionId == KeyEvent.KEYCODE_ENDCALL) {
                              roleName = etRole.text.toString()
                              roleSkill = etSkill.text.toString()
                              roleGender = etGender.text.toString()
                        }
                        false
                  }

                  btnInsert.setOnClickListener {
                        inputResult()
                  }

                  btnDeleteAll.setOnClickListener {
                        deleteAll()
                  }

                  btnQueryByGender.setOnClickListener {
                        inputGender()
                  }

                  btnDeleteByName.setOnClickListener {
                        inputRoleName()
                  }


            }
      }

      private fun inputRoleName() {
            if (!TextUtils.isEmpty(roleName)) {

                  deleteByName(roleName!!)
            } else {
                  UIUtils.toast("输入框无内容")
            }
      }

      private fun inputGender() {
            if (!TextUtils.isEmpty(roleGender)) {
                  if (gendersStr.contains(roleGender)) {
                        queryByGender(roleGender!!)
                  } else {
                        UIUtils.toast("输入的性别必须是 男 或 女 ~~~")
                  }

            } else {
                  UIUtils.toast("输入框无内容")
            }
      }

      private fun inputResult() {
            if (!TextUtils.isEmpty(roleName)
                  && !TextUtils.isEmpty(roleGender)
                  && !TextUtils.isEmpty(roleSkill)
            ) {
                  if (gendersStr.contains(roleGender)) {
                        addData(GameRole(roleName!!, roleGender!!, roleSkill!!))
                  } else {
                        UIUtils.toast("输入的性别必须是 男 或 女 ~~~")
                  }
            } else {
                  UIUtils.toast("输入框无内容")
            }
      }


      /**
       * 添加单个数据
       */
      private fun addData(role: GameRole) {
            dbManager
                  .insertRole(role)!!
                  .subscribe({
                        queryAlL()
                  }, {
                        Log.e(TAG, "insert data fail --> ${it.message} ")
                        UIUtils.toast("添加数据失败~")
                  })
      }


      private fun queryAlL() {
            dbManager
                  .queryAllData()!!
                  .subscribe({
                        if (it.size <= 0) {
                              UIUtils.toast("数据库的数据被清空~")
                        }
                        adapter.setData(it)
                  }, {
                        Log.e(TAG, "query data fail --> ${it.message} ")
                        UIUtils.toast("查询数据失败~")
                  })
      }


      private fun deleteAll() {
            dbManager
                  .deleteAll()!!
                  .subscribe({
                        queryAlL()
                  }, {
                        Log.e(TAG, "delete data fail --> ${it.message} ")
                        UIUtils.toast("数据删除失败~")
                  })
      }

      private fun queryByGender(gender: String) {
            dbManager
                  .queryByGender(gender)!!
                  .subscribe({
                        if (it.size > 0) {
                              adapter.setData(it)
                        } else {
                              UIUtils.toast("Sorry!!没有你要查的数据^_^")
                        }
                  }, {
                        Log.e(TAG, "query By Gender data fail --> ${it.message} ")
                        UIUtils.toast("查询关于${gender}的数据失败~")
                  })
      }

      private fun deleteByName(name: String) {


            dbManager
                  .deleteByName(name)!!
                  .subscribe({
                        queryAlL()
                  }, {
                        Log.e(TAG, "delete data fail --> ${it.message} ")
                        UIUtils.toast("${name}删除失败~")
                  })

      }
}