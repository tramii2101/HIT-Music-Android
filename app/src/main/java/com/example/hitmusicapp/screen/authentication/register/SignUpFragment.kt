package com.example.hitmusicapp.screen.authentication.register

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.FragmentManager
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentSignUpBinding
import com.example.hitmusicapp.entity.RegisterRequest
import com.example.hitmusicapp.entity.RegisterResponse
import com.example.hitmusicapp.retrofit.ApiService
import com.example.hitmusicapp.retrofit.RetrofitClient
import com.example.hitmusicapp.screen.authentication.login.LoginActivity
import com.example.hitmusicapp.utils.extension.ExtensionFunction.toast
import com.example.hitmusicapp.utils.extension.ExtensionFunction.toast_long
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    private var progressDialog: ProgressDialog? = null
    override fun initListener() {
        binding.backLogin.setOnClickListener {
            editor.putString("fragmentReplace", "SignInFragment")
            editor.apply()
            requireActivity().supportFragmentManager.popBackStack(
                "SignInFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }


        binding.tvSignIn.setOnClickListener {
            editor.putString("fragmentReplace", "SignInFragment")
            editor.apply()
            requireActivity().supportFragmentManager.popBackStack(
                "SignInFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        binding.signUp.setOnClickListener {
            signUp()
        }
        binding.confirmPassword.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event?.keyCode == KeyEvent.KEYCODE_ENTER) {
                signUp()

            }
            return@setOnEditorActionListener true
        }


        var isHidden = true
        binding.icPassword.setOnClickListener {
            isHidden = !isHidden
            if (isHidden) {
                //hide password
                binding.icPassword.setImageResource(R.drawable.hide_pass)
                binding.password.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            } else {
                //show password
                binding.icPassword.setImageResource(R.drawable.show_password)
                binding.password.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }

        var isHiddenPassword = true
        binding.icPasswordVisibility.setOnClickListener {
            isHiddenPassword = !isHiddenPassword
            if (isHiddenPassword) {
                //hide password
                binding.icPasswordVisibility.setImageResource(R.drawable.hide_pass)
                binding.confirmPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            } else {
                //show password
                binding.icPasswordVisibility.setImageResource(R.drawable.show_password)
                binding.confirmPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }
    }

    private fun signUp() {
        val fullname = binding.fullName.text.toString()
        val username = binding.userName.text.toString()
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val passwordAgain = binding.confirmPassword.text.toString()
        if (fullname == "" || username == "" || email == "" || password == "" || passwordAgain == "") {
            requireContext().toast(getString(R.string.data_cannot_be_left_blank))
        } else {
            if (password != passwordAgain) {
                requireContext().toast(getString(R.string.password_andpassword_again_must_be_same))
            } else {
                progressDialog =
                    ProgressDialog.show(context, null, "Registering ,please wait...", true)
                val apiService = RetrofitClient.getInstance().create(ApiService::class.java)
                val registerRequest =
                    RegisterRequest(fullname, username, email, password, passwordAgain)
                val call = apiService.register(registerRequest)
                call.enqueue(object : retrofit2.Callback<RegisterResponse> {
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        progressDialog?.dismiss()
                        if (response.isSuccessful) {
                            val registerResponseData = response?.body()?.data
                            Log.e("aaaaaaa3", "$registerResponseData")
                            editor.putString("fragmentReplace", "SignInFragment")
                            editor.putString("user", "${registerResponseData?.username.toString()}")
                            editor.apply()
//                            requireActivity().supportFragmentManager.popBackStack(
//                                "SignInFragment",
//                                FragmentManager.POP_BACK_STACK_INCLUSIVE
//                            )
                            val intent = Intent(requireContext(), LoginActivity::class.java)
                            startActivity(intent)
                            requireContext().toast(getString(R.string.sig_up_success))
                        } else {
                            val errorBody = response.errorBody()?.string()
                            val errorJson = JSONObject(errorBody)
                            val message = errorJson.getString("message")
                            requireContext().toast_long(message)

                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        progressDialog?.dismiss()
                        requireContext().toast("${t.message}")
                    }
                })
            }
        }
    }

    override fun initView() {
        sharedPreferences = context?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
            ?: sharedPreferences
        editor = sharedPreferences.edit()
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
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }

}