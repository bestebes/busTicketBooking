package com.example.beste1.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.beste1.R
import com.example.beste1.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AccountFragment : Fragment() {
    private  lateinit var  auth: FirebaseAuth
    private lateinit var binding: FragmentAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        auth = Firebase.auth
        binding.buttonKayit.setOnClickListener {
            val email = binding.EmailAddress.text.toString()
            val pass = binding.Password.text.toString()
            val pass2 = binding.sifreTekrar.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty() && pass2.isNotEmpty()){
                if (pass == pass2) {
                    auth.createUserWithEmailAndPassword(email, pass)
                        .addOnSuccessListener {
                            AlertDialog.Builder(requireContext())
                                .setTitle("Başarılı")
                                .setMessage("Hesabınız Başarıyla Oluşturuldu!")
                                .setPositiveButton("Tamam") { p1, p2 -> }
                                .show()
                        }
                        .addOnFailureListener {
                            AlertDialog.Builder(requireContext())
                                .setTitle("Hata")
                                .setMessage(it.localizedMessage)
                                .setPositiveButton("Tamam") { p1, p2 -> }
                                .show()
                        }
                }
                /*else{
                    Toast.makeText(this@AccountFragment, "Girilen şifreler aynı değil!", Toast.LENGTH_LONG).show()
                }*/
            }
        }
        binding.buttonGiris.setOnClickListener {
            val email = binding.EmailAddress.text.toString()
            val pass = binding.Password.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, pass)
                    .addOnSuccessListener {
                        AlertDialog.Builder(requireContext())
                            .setTitle("Başarılı")
                            .setMessage("Hesabınıza Başarıyla Giriş Yapıldı!")
                            .setPositiveButton("Tamam") { p1, p2 -> }
                            .show()
                        val membersFragment = MembersFragment()
                        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                        transaction.replace(R.id.frameLayout, membersFragment)
                        transaction.commit()
                    }
                    .addOnFailureListener {
                        AlertDialog.Builder(requireContext())
                            .setTitle("Hata")
                            .setMessage(it.localizedMessage)
                            .setPositiveButton("Tamam") { p1, p2 -> }
                            .show()
                        }
            }
        }
        binding.switchAuth.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked){
                binding.buttonGiris.visibility = View.INVISIBLE
                binding.buttonKayit.visibility = View.VISIBLE
                binding.sifreTekrar.visibility = View.VISIBLE
                binding.switchAuth.text = "Giriş Yap"
                binding.EmailAddress.text.clear()
                binding.Password.text.clear()
                binding.sifreTekrar.text.clear()
                binding.textView4.text = "Zaten bir hesabın var mı?"
            }
            else{
                binding.buttonGiris.visibility = View.VISIBLE
                binding.buttonKayit.visibility = View.INVISIBLE
                binding.sifreTekrar.visibility = View.INVISIBLE
                binding.EmailAddress.text.clear()
                binding.Password.text.clear()
                binding.textView4.text = "Henüz bir hesabın yok mu?"
                binding.switchAuth.text = "Kayıt Ol"
            }
        }
    }
}