package com.example.hitmusicapp.screen.authentication.enter_code

import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentEnterCodeBinding
import com.example.hitmusicapp.screen.authentication.create_new_password.CreateNewPasswordFragment

class EnterCodeFragment : BaseFragment<FragmentEnterCodeBinding>() {

    private val viewModel by lazy {
        activity?.let { ViewModelProvider(it)[EnterCodeViewModel::class.java] }
    }


    override fun handleEvent() {
        val sharedPreferences = requireActivity().getSharedPreferences("AUTHENTICATION", Context.MODE_PRIVATE)
        val tokenVerify = sharedPreferences.getString("tokenVerifyOTP", null)

        // Click verify and go to CreateNewPasswordFragment
        binding.tvVerifyOtp.setOnClickListener {
            if (tokenVerify != null) {
                viewModel?.otp = binding.otpView.text.toString()
                viewModel?.tokenVerifyOTP = tokenVerify
                viewModel?.getData()
                viewModel?.token?.observe(
                    this, Observer {
                        if (it != null && it != "") {
                            sharedPreferences.edit().putString("tokenResetPassword", it.toString()).apply()
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(R.id.frame_authen, CreateNewPasswordFragment())
                                .addToBackStack("forgot_password")
                                .commit()
                        } else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                        Log.e("token reset", it.toString())
                    }

                )
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

        }


        // Click arrow back and back to previous fragment (ForgotPasswordMethod)
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
                binding.tvResendCodeTime.text = " ${millisUntilFinished / 1000} "
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