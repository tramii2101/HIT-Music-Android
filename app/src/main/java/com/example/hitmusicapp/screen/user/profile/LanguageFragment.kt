package com.example.hitmusicapp.screen.user.profile


import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hitmusicapp.MainActivity
import com.example.hitmusicapp.R
import com.example.hitmusicapp.base.BaseFragment
import com.example.hitmusicapp.databinding.FragmentLanguageBinding
import com.example.hitmusicapp.utils.common.Constant


class LanguageFragment : BaseFragment<FragmentLanguageBinding>() {


    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    private lateinit var language: String

    @Suppress("SameParameterValue")
    override fun initListener() {
        binding.backLanguage.setOnClickListener {

            (activity as? MainActivity)?.onBackPressed()
        }
        binding.vi.setOnClickListener {
            selected("vi")
            unSelected("vi")

            if (language != "vi") onClickLanguage("vi")
        }
        binding.eng.setOnClickListener {
            selected("eng")
            unSelected("eng")

            if (language != "eng") onClickLanguage("eng")
        }
        binding.ko.setOnClickListener {
            selected("ko")
            unSelected("ko")

            if (language != "ko") onClickLanguage("ko")
        }
        binding.hi.setOnClickListener {
            selected("hi")
            unSelected("hi")

            if (language != "hi") onClickLanguage("hi")
        }
        binding.ja.setOnClickListener {
            selected("ja")
            unSelected("ja")

            if (language != "ja") onClickLanguage("ja")
        }
        binding.fr.setOnClickListener {
            selected("fr")
            unSelected("fr")

            if (language != "fr") onClickLanguage("fr")
        }
        binding.ar.setOnClickListener {
            selected("ar")
            unSelected("ar")

            if (language != "ar") onClickLanguage("ar")
        }
        binding.es.setOnClickListener {
            selected("es")
            unSelected("es")

            if (language != "es") onClickLanguage("es")
        }
        binding.ru.setOnClickListener {
            selected("ru")
            unSelected("ru")

            if (language != "ru") onClickLanguage("ru")
        }
        binding.`in`.setOnClickListener {
            selected("in")
            unSelected("in")

            if (language != "in") onClickLanguage("in")
        }

    }

    private fun unSelected(language: String) {
        if (language != "vi") binding.vi.setImageResource(R.drawable.circle_unselected)
        if (language != "eng") binding.eng.setImageResource(R.drawable.circle_unselected)
        if (language != "ar") binding.ar.setImageResource(R.drawable.circle_unselected)
        if (language != "es") binding.es.setImageResource(R.drawable.circle_unselected)
        if (language != "fr") binding.fr.setImageResource(R.drawable.circle_unselected)
        if (language != "hi") binding.hi.setImageResource(R.drawable.circle_unselected)
        if (language != "in") binding.`in`.setImageResource(R.drawable.circle_unselected)
        if (language != "ja") binding.ja.setImageResource(R.drawable.circle_unselected)
        if (language != "ru") binding.ru.setImageResource(R.drawable.circle_unselected)
        if (language != "ko") binding.ko.setImageResource(R.drawable.circle_unselected)
    }

    private fun selected(language: String) {
        if (language == "vi") binding.vi.setImageResource(R.drawable.circle_selected)
        if (language == "eng") binding.eng.setImageResource(R.drawable.circle_selected)
        if (language == "ar") binding.ar.setImageResource(R.drawable.circle_selected)
        if (language == "es") binding.es.setImageResource(R.drawable.circle_selected)
        if (language == "fr") binding.fr.setImageResource(R.drawable.circle_selected)
        if (language == "hi") binding.hi.setImageResource(R.drawable.circle_selected)
        if (language == "in") binding.`in`.setImageResource(R.drawable.circle_selected)
        if (language == "ja") binding.ja.setImageResource(R.drawable.circle_selected)
        if (language == "ru") binding.ru.setImageResource(R.drawable.circle_selected)
        if (language == "ko") binding.ko.setImageResource(R.drawable.circle_selected)
    }

    private fun onClickLanguage(language: String) {
        (activity as? MainActivity)?.updateLocale(language)
        editor.putString("language", language)
        editor.apply()
    }


    override fun initView() {
        sharedPreferences =
            context?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
                ?: sharedPreferences
        editor = sharedPreferences.edit()
        language = sharedPreferences.getString("language", Constant.languageDefault).toString()

        if (language == "vi") {
            binding.vi.setImageResource(R.drawable.circle_selected)
        } else {
            if (language == "ar") {
                binding.ar.setImageResource(R.drawable.circle_selected)
            } else {
                if (language == "es") {
                    binding.es.setImageResource(R.drawable.circle_selected)
                } else {
                    if (language == "fr") {
                        binding.fr.setImageResource(R.drawable.circle_selected)
                    } else {
                        if (language == "hi") {
                            binding.hi.setImageResource(R.drawable.circle_selected)
                        } else {
                            if (language == "in") {
                                binding.`in`.setImageResource(R.drawable.circle_selected)
                            } else {
                                if (language == "ja") {
                                    binding.ja.setImageResource(R.drawable.circle_selected)
                                } else {
                                    if (language == "ko") {
                                        binding.ko.setImageResource(R.drawable.circle_selected)
                                    } else {
                                        if (language == "ru") {
                                            binding.ru.setImageResource(R.drawable.circle_selected)
                                        } else {
                                            binding.eng.setImageResource(R.drawable.circle_selected)
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
    ): FragmentLanguageBinding {
        return FragmentLanguageBinding.inflate(inflater, container, false)
    }
}