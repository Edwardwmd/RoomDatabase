package com.edw.roomdatabase.adapter

import com.edw.roomdatabase.R
import com.edw.roomdatabase.base.BaseAdapter
import com.edw.roomdatabase.base.BaseViewHolder
import com.edw.roomdatabase.databinding.ItemCityBinding
import com.edw.roomdatabase.db.citydb.City

/*****************************************************************************************************
 * Project Name:    RoomDatabase
 *
 * Date:            2021-06-26
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
class CitySearchAdapter : BaseAdapter<City, ItemCityBinding>() {
  companion object{
             private const val TAG="CitySearchAdapter"
       }
      override fun initLayoutRes(): Int {
            return R.layout.item_city
      }

      override fun onDataBindViewHolder(
            holder: BaseViewHolder,
            binding: ItemCityBinding?,
            element: City,
            position: Int
      ) {
            binding?.apply {
                  chinaCity = element
                  executePendingBindings()
            }
      }


}