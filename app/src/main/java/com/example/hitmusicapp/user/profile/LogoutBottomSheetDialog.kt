package com.example.hitmusicapp.user.profile

import android.view.LayoutInflater
import com.example.hitmusicapp.base.BaseBottomSheetDialog
import com.example.hitmusicapp.databinding.BottomLogOutBinding

class LogoutBottomSheetDialog : BaseBottomSheetDialog<BottomLogOutBinding>() {
    private var logOutClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onYesClick()
        fun onNoClick()
    }

    fun setOnClickListener(listener: OnClickListener) {
        logOutClickListener = listener
    }

    override fun initListener() {
        binding.cancelLogout.setOnClickListener {
            logOutClickListener?.onNoClick()
        }
        binding.yesLogout.setOnClickListener {
            logOutClickListener?.onYesClick()

        }
    }

    override fun initData() {

    }

    override fun initView() {


    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater): BottomLogOutBinding {

        return BottomLogOutBinding.inflate(layoutInflater)
    }
}