package com.example.hitmusicapp.screen.authentication.login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.text.Editable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.example.hitmusicapp.MainActivity
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentSignInBinding
import com.example.hitmusicapp.entity.LoginRequest
import com.example.hitmusicapp.entity.LoginResponse
import com.example.hitmusicapp.retrofit.ApiService
import com.example.hitmusicapp.retrofit.RetrofitClient
import com.example.hitmusicapp.screen.authentication.forgot_methods.ForgotPasswordMethodsFragment
import com.example.hitmusicapp.screen.authentication.register.SignUpFragment
import com.example.hitmusicapp.utils.extension.ExtensionFunction.toast
import retrofit2.Call
import retrofit2.Response

class SignInFragment : BaseFragment<FragmentSignInBinding>() {
    private var progressDialog: ProgressDialog? = null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    var user: String = ""
    var password: String = ""

    override fun initListener() {
        binding.tvForgotPassword.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_authen, ForgotPasswordMethodsFragment())
                .addToBackStack("authentication").commit()
        }

        binding.tvSignUp.setOnClickListener {
            editor.putString("fragmentReplace", "SignUpFragment")
            editor.apply()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_authen, SignUpFragment()).addToBackStack("SignInFragment")
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

        binding.tvBtnSignIn.setOnClickListener {
            user = binding.edtEmailLogin.text.toString()
            password = binding.edtPasswordLogin.text.toString()
            LoginApp(user, password)


        }
        binding.edtPasswordLogin.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event?.keyCode == KeyEvent.KEYCODE_ENTER) {
                user = binding.edtEmailLogin.text.toString()
                password = binding.edtPasswordLogin.text.toString()
                LoginApp(user, password)
            }

            return@setOnEditorActionListener true
        }
        false
    }

    private fun LoginApp(user: String, password: String) {

        if (user == "" || password == "") {
            requireContext().toast(getString(R.string.username_and_password_cannot_be_left_blank))
        } else {
            progressDialog = ProgressDialog.show(context, null, "Logging in,please wait...", true)
            val apiService = RetrofitClient.getInstance().create(ApiService::class.java)
            val loginRequest = LoginRequest(user, password)
            val call = apiService.login(loginRequest)
            call.enqueue(object : retrofit2.Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    progressDialog?.dismiss()
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null) {
                            val accessToken = loginResponse.accessToken
                            editor.putString("accessToken", "$accessToken")
                        }
                        editor.putString("user", "$user")
                        editor.putString("password", "$password")
                        editor.apply()
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        requireContext().toast(getString(R.string.username_or_password_is_incorrect))

                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    progressDialog?.dismiss()
                    requireContext().toast("${t.message}")

                }
            })
        }

    }

    override fun initView() {
        sharedPreferences = context?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
            ?: sharedPreferences
        editor = sharedPreferences.edit()
        user = sharedPreferences.getString("user", "").toString()
        password = sharedPreferences.getString("password", "").toString()

        if (user != "" && password != "") {
            val s1: Editable = Editable.Factory.getInstance().newEditable(user)
            val s2: Editable = Editable.Factory.getInstance().newEditable(password)
            binding.edtEmailLogin.text = s1
            binding.edtPasswordLogin.text = s2
        } else {
            if (user != "") {
                Log.e("aaa", "$user")
                val editableText: Editable = Editable.Factory.getInstance().newEditable(user)
                binding.edtEmailLogin.text = editableText
            }
        }

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