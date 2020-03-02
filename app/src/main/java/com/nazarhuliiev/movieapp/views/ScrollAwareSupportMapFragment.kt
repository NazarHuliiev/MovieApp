package com.nazarhuliiev.movieapp.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.SupportMapFragment


class ScrollAwareSupportMapFragment : SupportMapFragment() {

    private var touchableWrapper : TouchableWrapper? = null

    override fun onCreateView(p0: LayoutInflater, p1: ViewGroup?, p2: Bundle?): View? {
        val view = super.onCreateView(p0, p1, p2) as ViewGroup

        touchableWrapper = TouchableWrapper(context!!)

        view.addView(
            touchableWrapper,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)

        return view
    }

    fun setListener(touchEvent: () -> Unit) {
        touchableWrapper!!.setEvent(touchEvent)
    }

    class TouchableWrapper(
        context: Context)
        : FrameLayout(context) {

        private var touchEvent: (() -> Unit)? = null

        init {
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        }

        fun setEvent(touchEvent: (() -> Unit)?) {
            this.touchEvent = touchEvent
        }

        override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
            when(ev!!.action) {
                MotionEvent.ACTION_UP -> touchEvent?.invoke()
                MotionEvent.ACTION_DOWN -> touchEvent?.invoke()
            }
            return super.dispatchTouchEvent(ev)
        }
    }
}