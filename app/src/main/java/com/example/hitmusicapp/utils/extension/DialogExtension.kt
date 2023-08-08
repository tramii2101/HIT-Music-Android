package com.example.hitmusicapp.utils.extension

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import com.example.hitmusicapp.databinding.DlLoadingCommonBinding

fun Dialog.start(stopFlag: Boolean = false, vb: ViewBinding) {
    val marginY = -170
    setContentView(vb.root)
    window?.apply {
        setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        attributes.apply {
            y = marginY
            gravity = Gravity.CENTER
        }
    }
    setCancelable(!stopFlag)
    show()
}

fun Dialog.start(stopFlag: Boolean = false) {
    fun Dialog.start(stopFlag: Boolean = false, vb: ViewBinding) {
        val marginY = -170
        val binding = DlLoadingCommonBinding.inflate(layoutInflater)
        window?.apply {
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes.apply {
                y = marginY
                gravity = Gravity.CENTER
            }
        }
        setCancelable(!stopFlag)
        show()
    }
}