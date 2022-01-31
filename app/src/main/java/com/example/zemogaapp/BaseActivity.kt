package com.example.zemogaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.zemogaapp.utils.ProgressDialogFragment

open class BaseActivity : AppCompatActivity() {
    private val progressDialogFragment = ProgressDialogFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showLoading() {

        if (!progressDialogFragment.isVisible && !progressDialogFragment.isAdded) {
            progressDialogFragment.setStyle(
                DialogFragment.STYLE_NO_TITLE,
                R.style.LoadingDialogTheme
            )
            progressDialogFragment.show(supportFragmentManager, ProgressDialogFragment.TAG)
        }
    }

    fun dismissLoading() {

        try {
            progressDialogFragment.dismiss()
        } catch (e: Exception) {
            println(e.stackTrace)
        }


    }
}