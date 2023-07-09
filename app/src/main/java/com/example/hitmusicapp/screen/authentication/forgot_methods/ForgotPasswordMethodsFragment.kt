package com.example.hitmusicapp.screen.authentication.forgot_methods

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentForgotPasswordMethodsBinding
import com.example.hitmusicapp.screen.authentication.enter_code.EnterCodeFragment

class ForgotPasswordMethodsFragment : BaseFragment<FragmentForgotPasswordMethodsBinding>() {
    override fun initListener() {
        binding.tvMethodEmail.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_authen, EnterCodeFragment()).addToBackStack("forgot_password")
                .commit()
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