package com.edw.roomdatabase.mvvm

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewStub
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edw.roomdatabase.R
import com.edw.roomdatabase.adapter.AtomDecoration
import com.edw.roomdatabase.adapter.BookAdapter
import com.edw.roomdatabase.base.BaseAdapter
import com.edw.roomdatabase.base.BaseProActivity
import com.edw.roomdatabase.databinding.ActivityLiveDataWithRoomBinding
import com.edw.roomdatabase.db.livedata.Book
import com.edw.roomdatabase.utils.DialogUtils
import com.edw.roomdatabase.utils.Preconditions
import com.edw.roomdatabase.utils.UIUtils
import kotlinx.coroutines.launch

/**
 * 通过结合LiveData+ViewModel+Coroutines+Room实现简单的MVVM模式
 */
class LiveDataWithRoomActivity :
    BaseProActivity<ActivityLiveDataWithRoomBinding, LiveDataWithRoomViewModel>(), BaseAdapter.ItemClickListener<Book>,
    BaseAdapter.ItemLongClickListener<Book> {
    companion object {
        private const val TAG: String = "LiveDataWithRoomActivity"
    }

    private lateinit var addButton: Button
    private lateinit var mRecy: RecyclerView
    private var emptyLayout: ViewStub? = null
    private val adapter by lazy { BookAdapter() }

    override fun initLayoutRes(): Int {
        return R.layout.activity_live_data_with_room
    }

    override fun requestDataViewModel(): Class<LiveDataWithRoomViewModel> {
        return LiveDataWithRoomViewModel::class.java
    }


    @SuppressLint("LongLogTag")
    override fun initData() {
        bind.click = ProxyClick()
        addButton = bind.add
        mRecy = bind.mRecy
        emptyLayout = bind.emptyRepo.viewStub
        if (emptyLayout == null) {
            emptyLayout?.inflate()
        }
        mRecy.layoutManager = LinearLayoutManager(this)
        mRecy.setHasFixedSize(true)
        mRecy.addItemDecoration(AtomDecoration())
        mRecy.adapter = adapter
        adapter.setItemClickListener(this)
        adapter.setItemLongClickListener(this)
        launch {
            vm.showResults()?.observe(this@LiveDataWithRoomActivity) {
                Preconditions.checkNull(it)
                if (it.size > 0) {
                    if (mRecy.visibility == View.GONE) mRecy.visibility = View.VISIBLE
                    if (emptyLayout?.visibility == View.VISIBLE) emptyLayout?.visibility = View.GONE
                } else {
                    emptyLayout?.visibility = View.VISIBLE
                    mRecy.visibility = View.GONE
                }
                adapter.setData(it)
            }
        }

    }

    /**
     * 点击显示单个条目数据
     */
    override fun itemClick(v: View, element: Book, position: Int) {
        UIUtils.toast("Position= $position  BookName= ${element.bookName}")
    }

    /**
     * 长按删除单条数据
     */
    override fun itemLongClick(v: View, element: Book, position: Int) {
        DialogUtils.dialogCreate(this,element.bookName).setPositiveButton(object : DialogUtils.PositiveButton {
            override fun confirmResult() {
                vm.deleteBook(element)
            }
        })
    }


    /**
     * 代理点击，实现布局与此类中的方法绑定，可以实时触发添加数据和删除数据
     */
    inner class ProxyClick {
        /**
         * 添加数据时添加按键绑定此方法
         */
        fun addData() {
            vm.addBook(
                Book("三国演义", "三英战吕布"),
                Book("水浒传", "武松景阳冈打大虫"),
                Book("西游记", "真假美猴王"),
                Book("红楼梦", "刘姥姥逛大观园"),
                Book("达芬奇密码", "解密达芬奇"),
                Book("自驾游指南", "老北京的生活圈"),
                Book("三十六计", "围魏救赵"))
        }

        /**
         * 删除按键绑定此方法
         */
        fun deleteAll() {
            vm.deleteAll()
        }
    }

}
