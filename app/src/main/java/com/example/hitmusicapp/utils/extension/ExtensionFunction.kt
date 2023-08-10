package com.example.hitmusicapp.utils.extension

import android.content.Context
import android.widget.Toast

object ExtensionFunction {
    fun Context.toast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    fun Context.toast_long(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }
}