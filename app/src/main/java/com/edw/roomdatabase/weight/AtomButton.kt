package com.edw.roomdatabase.weight

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.edw.roomdatabase.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

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
class AtomButton : AppCompatButton {

      companion object {

            private const val TAG = "AtomButton"
      }

      private var ls: AntiShakeClickListener? = null
      private var antiShakeDuration = 1000
      private var needAntiShake = false
      private val disposable by lazy { CompositeDisposable() }

      constructor(context: Context) : this(context, null)
      constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
      constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
            init(context, attrs)
      }

      private fun init(context: Context, attrs: AttributeSet?) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.AtomButton)
            antiShakeDuration = ta.getInt(R.styleable.AtomButton_antiShakeDuration, 1000)
            needAntiShake = ta.getBoolean(R.styleable.AtomButton_needAntiShake, false)
            ta.recycle()

            if (needAntiShake) {
                  disposable.add(
                        Observable.create(ObservableOnSubscribe<View> { element ->
                              setOnClickListener {
                                    element.onNext(it)
                              }
                        })
                              .throttleFirst(antiShakeDuration.toLong(), TimeUnit.MILLISECONDS)
                              .subscribeOn(Schedulers.io())
                              .observeOn(AndroidSchedulers.mainThread())
                              .subscribe({
                                    ls?.apply {
                                          antiShakeClick(it)
                                    }
                              }, {
                                    Log.e(TAG, "防抖动产生错误---》${it.message}")
                              })
                  )
            }
      }

      interface AntiShakeClickListener {

            fun antiShakeClick(view: View)
      }

      fun setAntiShakeListener(ls: AntiShakeClickListener) {
            this.ls = ls
      }

      //控件死亡时调用此方法来释放Rxjava
      fun dispose() {
            disposable.dispose()
      }
}