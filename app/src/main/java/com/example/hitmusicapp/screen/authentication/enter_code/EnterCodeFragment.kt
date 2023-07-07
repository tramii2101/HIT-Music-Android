package com.example.hitmusicapp.screen.authentication.enter_code

import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentEnterCodeBinding
import com.example.hitmusicapp.screen.authentication.create_new_password.CreateNewPasswordFragment

class EnterCodeFragment : BaseFragment<FragmentEnterCodeBinding>() {
    override fun handleEvent() {

        // Click verify and go to CreateNewPasswordFragment
        binding.tvVerifyOtp.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_authen, CreateNewPasswordFragment())
                .addToBackStack("forgot_password").commit()
        }

        binding.otpView.setText("")
        binding.otpView.setOtpCompletionListener {
            Log.d("Actual Value", it)
        }

        // Click to arrow back and back to previous fragment (ForgotPasswordMethod)
        binding.tvBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "forgot_password",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        // Countdown time to resend code
        var resendTime = 10000L
        object : CountDownTimer(resendTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvResendCodeTime.setText(" ${millisUntilFinished / 1000} ")
            }

            override fun onFinish() {
                binding.tvClickHere.isVisible = true
            }
        }.start()

        //click to resend code
        binding.tvClickHere.setOnClickListener {

        }
    }

    override fun bindData() {

    }

    override fun initListener() {

    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEnterCodeBinding {
        return FragmentEnterCodeBinding.inflate(inflater, container, false)
    }

}