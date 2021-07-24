package com.edw.roomdatabase.adapter

import com.edw.roomdatabase.R
import com.edw.roomdatabase.base.BaseAdapter
import com.edw.roomdatabase.base.BaseViewHolder
import com.edw.roomdatabase.databinding.ItemBookBinding
import com.edw.roomdatabase.db.livedata.Book

/*****************************************************************************************************
 * Project Name:    RoomDatabase
 *
 * Date:            2021-07-24
 *
 * Author:         EdwardWMD
 *
 * Github:          https://github.com/Edwardwmd
 *
 * Blog:            https://edwardwmd.github.io/
 *
 * Description:    BookAdapter
 ****************************************************************************************************
 */
class BookAdapter : BaseAdapter<Book, ItemBookBinding>() {

    override fun initLayoutRes(): Int {
        return R.layout.item_book
    }

    override fun onDataBindViewHolder(holder: BaseViewHolder, binding: ItemBookBinding?, element: Book, position: Int) {
        binding?.book = element
        binding?.executePendingBindings()
    }
}