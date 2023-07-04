package com.example.hitmusicapp.ui.authentication.create_new_password

import android.app.AlertDialog
import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentCreateNewPasswordBinding

class CreateNewPasswordFragment : BaseFragment<FragmentCreateNewPasswordBinding>() {
    val viewModel:CreateNewPasswordViewModel
        get() = ViewModelProvider(this).get(CreateNewPasswordViewModel::class.java)

    private val dialog by lazy { context?.let { Dialog(it) } }



    override fun initListener() {
        binding.tvChangePassword.setOnClickListener {

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
    ): FragmentCreateNewPasswordBinding {
        return FragmentCreateNewPasswordBinding.inflate(inflater, container, false)
    }

}