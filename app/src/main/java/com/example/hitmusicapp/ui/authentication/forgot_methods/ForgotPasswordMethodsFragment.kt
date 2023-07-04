package com.example.hitmusicapp.ui.authentication.forgot_methods

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentForgotPasswordMethodsBinding

class ForgotPasswordMethodsFragment : BaseFragment<FragmentForgotPasswordMethodsBinding>() {

    override fun initListener() {
        binding.tvEmailMethod.setOnClickListener {

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