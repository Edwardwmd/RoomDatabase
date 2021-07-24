package com.edw.roomdatabase.mvvm

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edw.roomdatabase.db.livedata.Book
import com.edw.roomdatabase.db.livedata.DataBaseRepository
import kotlinx.coroutines.launch

class LiveDataWithRoomViewModel : ViewModel(){
    companion object {
        private const val TAG: String = "LiveDataWithRoomViewModel"
    }
    suspend fun showResults(): LiveData<MutableList<Book>>? = DataBaseRepository.getInstance().getQueryAllData()

    @SuppressLint("LongLogTag")
    fun addBook(vararg book: Book) {
        viewModelScope.launch {
            DataBaseRepository.getInstance().addBook(*book)
        }
    }

    fun deleteBook(vararg book: Book) {
        viewModelScope.launch {
            DataBaseRepository.getInstance().deleteBook(*book)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            DataBaseRepository.getInstance().deleteAll()
        }
    }

}
