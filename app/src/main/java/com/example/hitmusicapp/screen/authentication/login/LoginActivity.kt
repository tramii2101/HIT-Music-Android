package com.example.hitmusicapp.screen.authentication.login


import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseActivity
import com.example.hitmusicapp.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor

    override fun initView() {
        sharedPreferences =
            this?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE) ?: sharedPreferences
        editor = sharedPreferences.edit()
        editor.putString("fragmentReplace", "SignInFragment")
        editor.apply()
        supportFragmentManager.beginTransaction().replace(R.id.frame_authen, SignInFragment())
            .commit()
    }

    override fun onBackPressed() {

        val fragmentReplace = sharedPreferences.getString("fragmentReplace", null)
        if (fragmentReplace == "SignInFragment") {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Exit App")
            alertDialog.setMessage("Do you want exit app?")

            alertDialog.setPositiveButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            alertDialog.setNegativeButton("Yes") { _, _ ->
                finishAffinity()
                super.onBackPressed()
            }
            alertDialog.show()
        }
        if (fragmentReplace == "SignUpFragment") {
            editor.putString("fragmentReplace", "SignInFragment")
            editor.apply()
            supportFragmentManager.popBackStack(
                "SignInFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }


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