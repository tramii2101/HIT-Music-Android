package com.example.hitmusicapp.screen.authentication.create_new_password


import android.app.Dialog
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.DlResetStateBinding
import com.example.hitmusicapp.databinding.FragmentCreateNewPasswordBinding
import com.example.hitmusicapp.utils.extension.start

class CreateNewPasswordFragment : BaseFragment<FragmentCreateNewPasswordBinding>() {
    val viewModel: CreateNewPasswordViewModel
        get() = ViewModelProvider(this).get(CreateNewPasswordViewModel::class.java)

    private val dialog by lazy { context?.let { Dialog(it) } }

    override fun handleEvent() {
        // Click to arrow back and back to previous fragment (ForgotPasswordMethod)
        binding.tvBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "forgot_password",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        // show hidden new password
        var hideNewPassword = true
        binding.icShowNewPassword.setOnClickListener {
            hideNewPassword = !hideNewPassword
            if (hideNewPassword) {
                //hide password
                binding.icShowNewPassword.setImageResource(R.drawable.hide_pass)
                binding.edtNewPw.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            } else {
                binding.icShowNewPassword.setImageResource(R.drawable.show_password)
                binding.edtNewPw.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }

        // show hidden retyped password
        var hideConfirmPw = true
        binding.icShowConfirmPassword.setOnClickListener {
            hideConfirmPw = !hideConfirmPw
            if (hideConfirmPw) {
                binding.icShowConfirmPassword.setImageResource(R.drawable.hide_pass)
                binding.edtRetypePw.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            } else {
                binding.icShowConfirmPassword.setImageResource(R.drawable.show_password)
                binding.edtRetypePw.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }

        binding.tvContinueChange.setOnClickListener {
            val viewBinding = DlResetStateBinding.inflate(layoutInflater)
            dialog?.start(false, viewBinding)
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
    ): FragmentCreateNewPasswordBinding {
        return FragmentCreateNewPasswordBinding.inflate(inflater, container, false)
    }

}