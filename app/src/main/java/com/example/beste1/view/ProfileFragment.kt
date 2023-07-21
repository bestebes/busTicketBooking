package com.example.beste1.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beste1.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        binding.buttonKaydet.setOnClickListener {
            if(binding.radioButtonErkek.isChecked)
            {
                val cinsiyet = "erkek"
            }

            if(binding.radioButtonKadin.isChecked)
            {
                val cinsiyet = "kadın"
            }
            val ad = binding.editTextTextPersonNameAd.text.toString()
            val soyad = binding.editTextTextPersonNameSoyad.text.toString()
            val mail = binding.editTextTextEmailAddress.text.toString()
            val pass = binding.editTextTextPassword.text.toString()

        }
        return binding.root
    }
}