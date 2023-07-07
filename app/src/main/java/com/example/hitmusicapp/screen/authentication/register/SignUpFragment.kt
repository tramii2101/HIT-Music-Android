package com.example.hitmusicapp.screen.authentication.register

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentSignUpBinding


class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    override fun initListener() {
        binding.backLogin.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack("authentication", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        binding.tvSignIn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_authen, SignUpFragment()).addToBackStack("authentication")
                .commit()
        }

        var isHidden = true
        binding.icPassword.setOnClickListener {
            isHidden = !isHidden
            if (isHidden) {
                //hide password
                binding.icPassword.setImageResource(R.drawable.hide_pass)
                binding.edtPasswordSignup.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            } else {
                //show password
                binding.icPassword.setImageResource(R.drawable.show_password)
                binding.edtPasswordSignup.transformationMethod =
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
    ): FragmentSignUpBinding {
        return  FragmentSignUpBinding.inflate(inflater, container, false)
    }

}