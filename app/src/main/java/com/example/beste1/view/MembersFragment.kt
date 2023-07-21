package com.example.beste1.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.beste1.R
import com.example.beste1.databinding.FragmentMembersBinding


class MembersFragment : Fragment() {
    private lateinit var binding: FragmentMembersBinding
    lateinit var myFragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMembersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.buttonCikis.setOnClickListener {
            (activity as MainActivity).signOut()
        }
        binding.buttonProfilim.setOnClickListener {
            myFragmentManager = parentFragmentManager
            fragmentTransaction = myFragmentManager.beginTransaction()
            val profileFragment = ProfileFragment()
            fragmentTransaction.replace(R.id.frameLayout, profileFragment).commit()
        }
        binding.buttonSeyahatlerim.setOnClickListener {
            myFragmentManager = parentFragmentManager
            fragmentTransaction = myFragmentManager.beginTransaction()
            val biletlerimFragment = BiletlerimFragment()
            fragmentTransaction.replace(R.id.frameLayout, biletlerimFragment).commit()
        }
    }
}