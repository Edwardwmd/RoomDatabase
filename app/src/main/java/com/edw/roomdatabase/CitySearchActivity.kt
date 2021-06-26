package com.edw.roomdatabase

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.edw.roomdatabase.adapter.AtomDecoration
import com.edw.roomdatabase.adapter.CitySearchAdapter
import com.edw.roomdatabase.base.BaseActivity
import com.edw.roomdatabase.databinding.ActivityCitySearchBinding
import com.edw.roomdatabase.db.CityManager
import com.edw.roomdatabase.db.SortType
import com.edw.roomdatabase.utils.ConstantUtils
import com.edw.roomdatabase.utils.UIUtils
import java.util.regex.Pattern

class CitySearchActivity : BaseActivity<ActivityCitySearchBinding>(), TextWatcher, TextView.OnEditorActionListener {
      companion object {

            private const val TAG = "CitySearchActivity"

      }

      private val cityManager by lazy { CityManager.getInstance(this) }
      private val adapter by lazy { CitySearchAdapter() }
      private var searchWord: String = ""


      override fun initLayoutRes(): Int {
            return R.layout.activity_city_search
      }

      override fun initData(savedInstanceState: Bundle?) {
            binding?.apply {
                  recyCity.layoutManager = LinearLayoutManager(this@CitySearchActivity)
                  recyCity.setHasFixedSize(true)
                  recyCity.addItemDecoration(AtomDecoration())
                  recyCity.adapter = adapter
                  etInput.addTextChangedListener(this@CitySearchActivity)
                  etInput.setOnEditorActionListener(this@CitySearchActivity)
                  btnAllAsc.setOnClickListener {
                        queryAll(SortType.ASC)
                  }
                  btnAllDesc.setOnClickListener {
                        queryAll(SortType.DESC)
                  }
                  btnQueryById.setOnClickListener {
                        queryById()
                  }
                  btnCityName.setOnClickListener {
                        queryByName()
                  }
                  btnProvince.setOnClickListener {
                        queryByProvince()
                  }
            }
      }

      /**
       * 根据城市名称查找城市相关信息
       */
      private fun queryByName() {
            val m = Pattern.compile(ConstantUtils.alphabetChinese).matcher(searchWord)
            if (m.matches()) {
                  cityManager
                        .queryByCityName(searchWord)!!
                        .subscribe({
                              if (it != null && it.size > 0) {
                                    adapter.setData(it)
                              } else {
                                    UIUtils.toast("数据库中没有关于 $searchWord 的数据")
                              }
                        }, {
                              UIUtils.toast("查询错误---》${it.message}")
                        })

            } else {
                  UIUtils.toast("请输入中文或拼音字母~~")
            }
      }

      /**
       * 通过省查找数据
       */
      private fun queryByProvince() {
            val m = Pattern.compile(ConstantUtils.alphabetChinese).matcher(searchWord)
            if (m.matches()) {
                  cityManager
                        .queryByCityProvince(searchWord)!!
                        .subscribe({
                              if (it != null && it.size > 0) {
                                    adapter.setData(it)
                              } else {
                                    UIUtils.toast("数据库中没有关于 $searchWord 的数据")
                              }
                        }, {
                              UIUtils.toast("查询错误---》${it.message}")
                        })

            } else {
                  UIUtils.toast("请输入中文或拼音字母")
            }
      }

      /**
       * 根据ID查找结果
       */
      private fun queryById() {
            val m = Pattern.compile(ConstantUtils.number).matcher(searchWord)
            if (m.matches()) {
                  cityManager
                        .queryById(searchWord.toInt())!!
                        .subscribe({
                              if (it != null && it.size > 0) {
                                    adapter.setData(it)
                              } else {
                                    UIUtils.toast("数据库没有你要查找的ID:$searchWord")
                              }
                        }, {
                              UIUtils.toast("查找错误---》${it.message}")
                        })
            } else {
                  UIUtils.toast("您输入的ID不是数字，请正确输入ID~")
            }
      }

      /**
       * 升序查询
       */
      private fun queryAll(sortType: String) {
            cityManager
                  .queryAll(sortType)!!
                  .subscribe({
                        if (it != null && it.size > 0) {
                              adapter.setData(it)
                        } else {
                              UIUtils.toast("数据库没有数据咯~~")
                        }
                  }, {
                        UIUtils.toast("数据库查询错误---》${it.message}")
                  })
      }


      /**
       * 模糊查询
       */
      private fun fuzzyQuery(s: Editable?) {
            if (!TextUtils.isEmpty(s.toString())) {
                  //模糊查询
                  cityManager
                        .queryByKeyWord(s.toString())!!
                        .subscribe({
                              adapter.setData(it)
                        }, {
                              Log.e(TAG, "查询数据库出错~~~")
                        })
            }
      }

      override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            Log.e(TAG, "KeyEvent ID-----> $actionId ")
            if (actionId == KeyEvent.KEYCODE_ENDCALL) {
                  binding?.apply {
                        searchWord = etInput.text.toString()
                  }
            }
            return false
      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

      }

      override fun afterTextChanged(s: Editable?) {
            fuzzyQuery(s)
      }


}