package com.example.hitmusicapp.utils.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun View.showKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.disableView() {
    this.isClickable = false
    this.postDelayed({ this.isClickable = true }, 500)
}

fun RecyclerView.setLinearLayoutManager(
    context: Context,
    adapter: RecyclerView.Adapter<*>,
    orientation: Int = RecyclerView.VERTICAL
) {
    val manager = LinearLayoutManager(context)
    manager.orientation = orientation
    this.layoutManager = manager
    this.adapter = adapter
}

fun RecyclerView.setGridLayoutManager(
    context: Context,
    adapter: RecyclerView.Adapter<*>,
    orientation: Int = RecyclerView.VERTICAL,
    spanCount: Int
) {
    val manager = GridLayoutManager(context, spanCount)
    manager.orientation = orientation
    this.layoutManager = manager
    this.adapter = adapter
}

