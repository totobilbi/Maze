package com.example.maze

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TypeWriter : TextView {
    private var mText: CharSequence? = null
    private var mIndex = 0
    private var mDelay: Long = 150 // in ms
    private val isDone = MutableLiveData<Boolean?>()

    constructor(context: Context?) : super(context) {
        isDone.value = false
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        isDone.value = false
    }

    private val mHandler = Handler()
    private val characterAdder: Runnable = object : Runnable {
        override fun run() {
            text = mText!!.subSequence(0, mIndex++)
            if (mIndex <= mText!!.length) {
                mHandler.postDelayed(this, mDelay)
            } else {
                Handler().postDelayed({
                    text = ""
                    isDone.setValue(true)
                }, 1000)
            }
        }
    }

    fun animateText(txt: CharSequence?) {
        mText = txt
        mIndex = 0
        text = ""
        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, mDelay)
    }

    fun setCharacterDelay(m: Long) {
        mDelay = m
    }

    fun isDone(): LiveData<Boolean?> {
        return isDone
    }
}