package com.example.hitmusicapp.screen.authentication.forgot_methods

import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentForgotPasswordMethodsBinding
import com.example.hitmusicapp.screen.authentication.enter_code.EnterCodeFragment
import com.example.hitmusicapp.utils.common.RegexCommon

class ForgotPasswordMethodsFragment : BaseFragment<FragmentForgotPasswordMethodsBinding>() {

    val viewModel: ForgotPasswordMethodsViewModel
        get() = ViewModelProvider(this)[ForgotPasswordMethodsViewModel::class.java]

    val sharedPreferences by lazy {
        requireActivity().getSharedPreferences("AUTHENTICATION", MODE_PRIVATE)
    }


    override fun initListener() {
        binding.tvSend.setOnClickListener {
            val verifyEmail = binding.edtEmail.text.toString().trim()
            if (RegexCommon.EMAIL.matches(verifyEmail)) {
                viewModel.email = verifyEmail
                viewModel.getTokenVerifyOTP(verifyEmail)

                viewModel.token.observe(this, Observer {
                    if (it != null) {
                        sharedPreferences.edit().putString("tokenVerifyOTP", it.toString()).apply()
                        sharedPreferences.edit().putString("verifyEmail", verifyEmail).apply()
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_authen, EnterCodeFragment())
                            .addToBackStack("forgot_password")
                            .commit()

                    } else {
                        Toast.makeText(context, viewModel.message.value, Toast.LENGTH_SHORT).show()
                    }
                })
                Log.e("data response", viewModel.token.value.toString())


            } else {
                Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show()
            }

        }

        binding.tvBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "authentication",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }


    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentForgotPasswordMethodsBinding {
        return FragmentForgotPasswordMethodsBinding.inflate(inflater, container, false)
    }


}
