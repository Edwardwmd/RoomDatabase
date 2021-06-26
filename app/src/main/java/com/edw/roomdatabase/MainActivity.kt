package com.edw.roomdatabase

import android.content.Intent
import android.os.Bundle
import com.edw.roomdatabase.base.BaseActivity
import com.edw.roomdatabase.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {


      companion object {

            private const val TAG = "MainActivity"
      }



      override fun initLayoutRes(): Int {
            return R.layout.activity_main
      }


      override fun initData(savedInstanceState: Bundle?) {
            binding?.apply {
                  btnLocal.setOnClickListener {
                        startActivity(Intent(this@MainActivity, CommonRoomActivity::class.java))
                  }

                  btnAssets.setOnClickListener {
                        startActivity(Intent(this@MainActivity, CitySearchActivity::class.java))
                  }
            }
      }


}