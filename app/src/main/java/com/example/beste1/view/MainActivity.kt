package com.example.beste1.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.beste1.R
import com.example.beste1.databinding.FragmentAraBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    lateinit var fragmentManager : FragmentManager
    lateinit var fragmentTransaction : FragmentTransaction
    private lateinit var binding: FragmentAraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = "ŞuBilet"//ActionBar title
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTransaction()
        val araFragment = AraFragment()
        fragmentTransaction.replace(R.id.frameLayout, araFragment).commit()

    }
    fun ucakFragment(view: View)
    {
        initTransaction()
        val ucakFragment = UcakFragment()
        fragmentTransaction.replace(R.id.frameLayout, ucakFragment).commit()
    }
    fun otobusFragment(view: View){
        initTransaction()
        val otobusFragment = OtobusFragment()
        fragmentTransaction.replace(R.id.frameLayout, otobusFragment).commit()
    }
    fun feribotFragment(view: View){
        initTransaction()
        val feribotFragment = FeribotFragment()
        fragmentTransaction.replace(R.id.frameLayout, feribotFragment).commit()
    }
    fun trenFragment(view: View){
        initTransaction()
        val trenFragment = TrenFragment()
        fragmentTransaction.replace(R.id.frameLayout, trenFragment).commit()
    }
    fun accountFragment(view: View){
        initTransaction()
        //TODO: session
        if (FirebaseAuth.getInstance().currentUser != null) {
            val membersFragment = MembersFragment()
            fragmentTransaction.replace(R.id.frameLayout, membersFragment).commit()
        } else {
            val accountFragment = AccountFragment()
            fragmentTransaction.replace(R.id.frameLayout, accountFragment).commit()
        }
    }
    fun araFragment(view: View){
        initTransaction()
        val araFragment = AraFragment()
        fragmentTransaction.replace(R.id.frameLayout, araFragment).commit()
    }
    fun helpFragment(view: View){
        initTransaction()
        //val helpFragment = HelpFragment()
        //fragmentTransaction.replace(R.id.frameLayout, helpFragment).commit()
    }
    fun travelFragment(view: View){
        initTransaction()
        //val travelFragment = TravelFragment()
        //fragmentTransaction.replace(R.id.frameLayout, travelFragment).commit()
    }
    fun biletBulOtobusFragment(view: View){
        initTransaction()
        val biletBulFragment = BiletBulFragment()
        fragmentTransaction.replace(R.id.frameLayout, biletBulFragment).commit()
    }
    fun signOut(){
        initTransaction()
        FirebaseAuth.getInstance().signOut()
        val accountFragment = AccountFragment()
        fragmentTransaction.replace(R.id.frameLayout, accountFragment).commit()
    }
    fun initTransaction() {
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
    }
}