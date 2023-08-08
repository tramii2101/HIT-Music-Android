package com.example.hitmusicapp.utils.extension

import android.content.res.Resources

object Constant {
    var languageDefault = Resources.getSystem().configuration.locales[0].language!!
    const val READ_TIME_OUT: Long = 30
    const val CONNECT_TIME_OUT: Long = 30
}