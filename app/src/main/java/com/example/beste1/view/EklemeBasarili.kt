package com.example.beste1.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.beste1.R
import com.example.beste1.databinding.FragmentEklemeBasariliBinding

class EklemeBasarili : Fragment() {
    lateinit var myFragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var binding: FragmentEklemeBasariliBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEklemeBasariliBinding.inflate(layoutInflater)
        binding.buttonEklemeBasarili.setOnClickListener {
            myFragmentManager = parentFragmentManager
            fragmentTransaction = myFragmentManager.beginTransaction()
            val araFragment = AraFragment()
            fragmentTransaction.replace(R.id.frameLayout, araFragment).commit()
        }
        return binding.root
    }
}