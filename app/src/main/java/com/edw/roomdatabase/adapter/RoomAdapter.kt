package com.edw.roomdatabase.adapter

import com.edw.roomdatabase.R
import com.edw.roomdatabase.base.BaseAdapter
import com.edw.roomdatabase.base.BaseViewHolder
import com.edw.roomdatabase.databinding.ItemRoomBinding
import com.edw.roomdatabase.db.gameroledb.GameRole

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
class RoomAdapter : BaseAdapter<GameRole, ItemRoomBinding>() {

      override fun initLayoutRes(): Int {
            return R.layout.item_room
      }

      override fun onDataBindViewHolder(
            holder: BaseViewHolder,
            binding: ItemRoomBinding?,
            element: GameRole,
            position: Int
      ) {
            binding?.apply {
                  gameRoleInfo = element
                  executePendingBindings()
            }
      }


}