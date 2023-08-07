package com.example.hitmusicapp.screen.authentication.create_new_password


import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.DlResetStateBinding
import com.example.hitmusicapp.databinding.FragmentCreateNewPasswordBinding
import com.example.hitmusicapp.screen.authentication.login.SignInFragment
import com.example.hitmusicapp.utils.extension.start

class CreateNewPasswordFragment : BaseFragment<FragmentCreateNewPasswordBinding>() {
    private val viewModel by lazy {
        activity?.let { ViewModelProvider(it) }?.get(CreateNewPasswordViewModel::class.java)
    }

    private val sharedPreferences: SharedPreferences by lazy {
        requireActivity().getSharedPreferences("AUTHENTICATION", Context.MODE_PRIVATE)
    }

    private val dialog by lazy { context?.let { Dialog(it) } }

    override fun handleEvent() {
        // Click to arrow back and back to previous fragment (ForgotPasswordMethod)
        binding.tvBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(
                "forgot_password",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        val tokenResetPassword = sharedPreferences.getString("tokenResetPassword", null)

        binding.tvContinueChange.setOnClickListener {
            val newPassword = binding.edtNewPw.text.toString().trim()
            val newPasswordAgain = binding.edtRetypePw.text.toString()
            if (newPassword.equals(newPasswordAgain)) {
                if (tokenResetPassword != null) {
                    viewModel?.newPassword = newPassword
                    viewModel?.tokenResetPassword = tokenResetPassword
                    viewModel?.createNewPassword()
                    viewModel?.status?.observe(this, Observer {
                        if (it == 200) {
                            val viewBinding = DlResetStateBinding.inflate(layoutInflater)
                            dialog?.start(false, viewBinding)
                            Handler().postDelayed(
                                {
                                    requireActivity().supportFragmentManager.beginTransaction()
                                        .replace(R.id.frame_authen, SignInFragment()).commit()
                                }, 1500
                            )

                        } else {
                            Toast.makeText(context, viewModel!!.message.value, Toast.LENGTH_SHORT)
                                .show()
                        }
                        Log.e("status reset", it.toString())
                    })
                }

            } else {
                Toast.makeText(context, viewModel?.message?.value.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // show hidden new password
        var hideNewPassword = true
        binding.icShowNewPassword.setOnClickListener {
            hideNewPassword = !hideNewPassword
            showAndHidePassword(hideNewPassword, binding.edtNewPw, binding.icShowNewPassword)
        }

        // show hidden retyped password
        var hideConfirmPw = true
        binding.icShowConfirmPassword.setOnClickListener {
            hideConfirmPw = !hideConfirmPw
            showAndHidePassword(hideConfirmPw, binding.edtRetypePw, binding.icShowConfirmPassword)
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

    private fun showAndHidePassword(hideConfirmPw: Boolean, edittext: EditText, icon: ImageView) {
        if (hideConfirmPw) {
            icon.setImageResource(R.drawable.hide_pass)
            edittext.transformationMethod =
                PasswordTransformationMethod.getInstance()
        } else {
            icon.setImageResource(R.drawable.show_password)
            edittext.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
        }
    }
}