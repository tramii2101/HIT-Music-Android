package com.example.hitmusicapp.screen.authentication.create_new_password


import android.app.Dialog
import android.content.Intent
import android.os.Handler
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.DlResetStateBinding
import com.example.hitmusicapp.databinding.FragmentCreateNewPasswordBinding
import com.example.hitmusicapp.screen.authentication.login.LoginActivity
import com.example.hitmusicapp.screen.authentication.login.SignInFragment
import com.example.hitmusicapp.utils.common.RegexCommon
import com.example.hitmusicapp.utils.extension.start

class CreateNewPasswordFragment : BaseFragment<FragmentCreateNewPasswordBinding>() {
    val viewModel: CreateNewPasswordViewModel by lazy {
        ViewModelProvider(this)[CreateNewPasswordViewModel::class.java]
    }


    private val dialog by lazy { context?.let { Dialog(it) } }

    val newPassword = binding.edtNewPw.toString()
    val newPasswordAgain = binding.edtRetypePw.toString()

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
            if (passwordIsOkay()) {
                if (newPassword == newPasswordAgain) {
                    viewModel.newPassword = newPassword
                    viewModel.createNewPassword()
                    viewModel.status.observe(this, Observer {
                        if (it == 400) {
                            val viewBinding = DlResetStateBinding.inflate(layoutInflater)
                            dialog?.start(false, viewBinding)
                            Handler().postDelayed(
                                {
                                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_authen, SignInFragment()).commit()
                                }, 1500
                            )

                        } else {
                            Toast.makeText(context, viewModel.message.value, Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                }
            } else {
                Toast.makeText(context, "Password is invalid", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun passwordIsOkay(): Boolean {
        return (RegexCommon.PASSWORD.matches(newPassword) && RegexCommon.PASSWORD.matches(
            newPasswordAgain
        ))
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