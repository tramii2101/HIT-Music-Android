package com.example.hitmusicapp.screen.authentication.login

import android.view.LayoutInflater
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseActivity
import com.example.hitmusicapp.databinding.ActivityLoginBinding

class LoginActivity :  BaseActivity<ActivityLoginBinding>(){

    override fun initView() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_authen, SignInFragment()).commit()
    }

    override fun initListener() {

    }

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun inflateViewBinding(layoutInflater: LayoutInflater): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

}