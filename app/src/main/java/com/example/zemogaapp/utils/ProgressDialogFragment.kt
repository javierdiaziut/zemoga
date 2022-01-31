package com.example.zemogaapp.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.zemogaapp.databinding.CustomProgressDialogBinding

class ProgressDialogFragment : DialogFragment() {

    private lateinit var binding: CustomProgressDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CustomProgressDialogBinding.inflate(inflater, container, false)
        dialog?.let {
            isCancelable = false
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dismiss()
    }

    companion object {
        fun newInstance() = ProgressDialogFragment()
        const val TAG = "ProgressDialogFragment"
    }
}
