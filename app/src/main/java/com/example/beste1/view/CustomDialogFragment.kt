package com.example.beste1.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.beste1.databinding.FragmentCustomDialogBinding
import com.example.beste1.databinding.FragmentKoltukSecimiBinding

class CustomDialogFragment(val onClick:(String) -> Unit): DialogFragment() {
    private lateinit var bindingDialog: FragmentCustomDialogBinding
    private lateinit var bindingKoltukSecimi: FragmentKoltukSecimiBinding
    lateinit var myFragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bindingDialog = FragmentCustomDialogBinding.inflate(LayoutInflater.from(context))
        bindingKoltukSecimi = FragmentKoltukSecimiBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(bindingDialog.root)
        val bundle = Bundle()

        bindingDialog.buttonKadin.setOnClickListener {
            val koltukSecimi = KoltukSecimi()
            koltukSecimi.arguments = bundle
            //TODO: cinsiyet problemi
            onClick("kadın")
            dialog?.dismiss()
        }

        bindingDialog.buttonErkek.setOnClickListener {
            val koltukSecimi = KoltukSecimi()
            koltukSecimi.arguments = bundle
            onClick("erkek")
            dialog?.dismiss()
        }

        val dialog = builder.create()

        return dialog
    }
}