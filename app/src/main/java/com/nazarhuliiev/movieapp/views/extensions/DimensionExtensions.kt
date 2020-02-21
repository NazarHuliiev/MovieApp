package com.nazarhuliiev.movieapp.views.extensions

import android.content.res.Resources

fun Int.dpToPx(): Float = (this * Resources.getSystem().displayMetrics.density)

fun Int.pxToDp(): Float = (this / Resources.getSystem().displayMetrics.density)