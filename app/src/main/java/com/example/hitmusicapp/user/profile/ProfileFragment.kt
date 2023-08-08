package com.example.hitmusicapp.user.profile


import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.hitmusicapp.MainActivity
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentProfileBinding
import com.example.hitmusicapp.entity.ProfileResponse
import com.example.hitmusicapp.retrofit.ApiService
import com.example.hitmusicapp.retrofit.RetrofitClient
import com.example.hitmusicapp.utils.extension.Constant
import com.example.hitmusicapp.utils.extension.ExtensionFunction.toast
import retrofit2.Call
import retrofit2.Response

interface OnItemClickListener {
    fun onReplaceFragment(fragment: Fragment)
}

var onItemClickListener: OnItemClickListener? = null

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var switch: Switch
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var language: String

    var nightMode: Boolean = true

    override fun initListener() {


        if (nightMode) {


        } else {


            switch.isChecked = true
        }
        editor.apply()

        switch.setOnClickListener {
            nightMode = sharedPreferences.getBoolean("nightMode", true)
            if (nightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("nightMode", false)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("nightMode", true)
            }
            editor.apply()
        }
        binding.logoutProfile.setOnClickListener {
            val logoutBottomSheetDialog = LogoutBottomSheetDialog()
            logoutBottomSheetDialog.show(parentFragmentManager, "")
            logoutBottomSheetDialog.setOnClickListener(object :
                LogoutBottomSheetDialog.OnClickListener {
                override fun onYesClick() {

                    (activity as MainActivity)?.back()
                }

                override fun onNoClick() {
                    logoutBottomSheetDialog.dismiss()
                }
            })


        }


        binding.languageProfile.setOnClickListener {
            onItemClickListener?.onReplaceFragment(LanguageFragment())
        }

        binding.profileSecurity.setOnClickListener {
            onItemClickListener?.onReplaceFragment(SecurityFragment())
        }

        binding.profileDataStorage.setOnClickListener {
            onItemClickListener?.onReplaceFragment(DataStorageFragment())
        }

        binding.profilePlayback.setOnClickListener {
            onItemClickListener?.onReplaceFragment(PlaybackFragment())
        }

        binding.profileAudioVideo.setOnClickListener {
            onItemClickListener?.onReplaceFragment(AudioVideoFragment())
        }

        binding.profileNotification.setOnClickListener {
            onItemClickListener?.onReplaceFragment(NotificationFragment())
        }

        binding.profileDetails.setOnClickListener {
            onItemClickListener?.onReplaceFragment(ProfileDetailsFragment())
        }
    }


    override fun initView() {
        sharedPreferences = context?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
            ?: sharedPreferences
        nightMode = sharedPreferences.getBoolean("nightMode", false)
        switch = binding.sw
        editor = sharedPreferences.edit()
        language = sharedPreferences.getString("language", Constant.languageDefault).toString()
        setDefaultLanguage(language)
        val accessToken = sharedPreferences.getString("accessToken", "")
        Log.e("adsadsa", "$accessToken")
        val apiService = RetrofitClient.getInstance().create(ApiService::class.java)
        val call = apiService.getProfile("Bearer $accessToken")
        call?.enqueue(object : retrofit2.Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful) {
                    val profileResponse = response.body()
                    binding.userName.text = profileResponse?.data?.fullname
                    binding.email.text = profileResponse?.data?.email
                    val url = profileResponse?.data?.avatar
                    Glide.with(binding.root)
                        .load(url)
                        .placeholder(R.drawable.logo)
                        .into(binding.avatar)
                } else {
                    Log.e("adsadsa11", "$response.body()")
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                requireActivity().toast("${t.message}")
            }
        })

    }

    private fun setDefaultLanguage(language: String) {

        if (language == "vi") {
            binding.languageDefault.text = getString(R.string.vietnam)
        } else {
            if (language == "ar") {
                binding.languageDefault.text = "العربية"
            } else {
                if (language == "es") {
                    binding.languageDefault.text = "Español"
                } else {
                    if (language == "fr") {
                        binding.languageDefault.text = "Français"
                    } else {
                        if (language == "hi") {
                            binding.languageDefault.text = "हिंदी"
                        } else {
                            if (language == "in") {
                                binding.languageDefault.text = "Indonesia"
                            } else {
                                if (language == "ja") {
                                    binding.languageDefault.text = "日本語"
                                } else {
                                    if (language == "ko") {
                                        binding.languageDefault.text = "한국어"
                                    } else {
                                        if (language == "ru") {
                                            binding.languageDefault.text = "Русский"
                                        } else {
                                            binding.languageDefault.text =
                                                getString(R.string.english)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
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
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(
            inflater,
            container,
            false
        )
    }

}