package com.example.hitmusicapp.screen.authentication.login

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentSignInBinding
import com.example.hitmusicapp.screen.authentication.forgot_methods.ForgotPasswordMethodsFragment
import com.example.hitmusicapp.screen.authentication.register.SignUpFragment

class SignInFragment : BaseFragment<FragmentSignInBinding>() {
    override fun initListener() {
        binding.tvForgotPassword.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_authen, ForgotPasswordMethodsFragment())
                .addToBackStack("authentication").commit()
        }

        binding.tvSignUp.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_authen, SignUpFragment()).addToBackStack("authentication")
                .commit()
        }

        var isHidden = true
        binding.icShowPassword.setOnClickListener {
            isHidden = !isHidden
            if (isHidden) {
                //hide password
                binding.icShowPassword.setImageResource(R.drawable.hide_pass)
                binding.edtPasswordLogin.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            } else {
                //show password
                binding.icShowPassword.setImageResource(R.drawable.show_password)
                binding.edtPasswordLogin.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
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
    ): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(inflater, container, false)
    }

}