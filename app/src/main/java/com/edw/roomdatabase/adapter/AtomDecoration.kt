package com.edw.roomdatabase.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.edw.roomdatabase.utils.UIUtils

/*****************************************************************************************************
 * Project Name:    RoomDatabase
 *
 * Date:            2021-06-25
 *
 * Author:         EdwardWMD
 *
 * Github:          https://github.com/Edwardwmd
 *
 * Blog:            https://edwardwmd.github.io/
 *
 * Description:    RecyclerView装饰物~~~~
 ****************************************************************************************************
 */
class AtomDecoration: RecyclerView.ItemDecoration() {

      override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            val position = parent.getChildAdapterPosition(view)

            //对Item设置MarginTop
            if (position==0){
                  outRect.top=UIUtils.dp2Px(parent.context,1)
            }else{
                  outRect.top=UIUtils.dp2Px(parent.context,10)
            }
            //对Item设置MarginLeft、MarginRight
            outRect.left=UIUtils.dp2Px(parent.context,15)
            outRect.right=UIUtils.dp2Px(parent.context,15)
      }
}