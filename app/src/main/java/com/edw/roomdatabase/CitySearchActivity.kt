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
       * ??????????????????????????????????????????
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
                                    UIUtils.toast("???????????????????????? $searchWord ?????????")
                              }
                        }, {
                              UIUtils.toast("????????????---???${it.message}")
                        })

            } else {
                  UIUtils.toast("??????????????????????????????~~")
            }
      }

      /**
       * ?????????????????????
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
                                    UIUtils.toast("???????????????????????? $searchWord ?????????")
                              }
                        }, {
                              UIUtils.toast("????????????---???${it.message}")
                        })

            } else {
                  UIUtils.toast("??????????????????????????????")
            }
      }

      /**
       * ??????ID????????????
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
                                    UIUtils.toast("??????????????????????????????ID:$searchWord")
                              }
                        }, {
                              UIUtils.toast("????????????---???${it.message}")
                        })
            } else {
                  UIUtils.toast("????????????ID??????????????????????????????ID~")
            }
      }

      /**
       * ????????????
       */
      private fun queryAll(sortType: String) {
            cityManager
                  .queryAll(sortType)!!
                  .subscribe({
                        if (it != null && it.size > 0) {
                              adapter.setData(it)
                        } else {
                              UIUtils.toast("????????????????????????~~")
                        }
                  }, {
                        UIUtils.toast("?????????????????????---???${it.message}")
                  })
      }


      /**
       * ????????????
       */
      private fun fuzzyQuery(s: Editable?) {
            if (!TextUtils.isEmpty(s.toString())) {
                  //????????????
                  cityManager
                        .queryByKeyWord(s.toString())!!
                        .subscribe({
                              adapter.setData(it)
                        }, {
                              Log.e(TAG, "?????????????????????~~~")
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