package com.example.beste1.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.beste1.model.OtobusSeferlerModel
import com.example.beste1.R
import com.example.beste1.databinding.FragmentOtobusBiletiAlBilgiGirisBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class OtobusBiletiAlBilgiGiris : Fragment() {
    lateinit var myFragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction
    private  lateinit var  auth: FirebaseAuth
    private lateinit var seferID: String
    private lateinit var database: DatabaseReference
    private lateinit var binding: FragmentOtobusBiletiAlBilgiGirisBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = Firebase.auth
        database = Firebase.database.reference
        binding = FragmentOtobusBiletiAlBilgiGirisBinding.inflate(layoutInflater)

        val nereden = arguments?.getString("nereden")
        val nereye = arguments?.getString("nereye")
        val kalkis = arguments?.getString("kalkis")
        val firmaAd = arguments?.getString("firmaAd")
        val ucret = arguments?.getInt("ucret")
        val sure = arguments?.getString("sure")
        val koltukNo = arguments?.getInt("koltukNo")
        val cinsiyet = arguments?.getString("cinsiyet")
        seferID = nereden + "00" + nereye + "00" + firmaAd + "00" + ucret + "00" + sure!!.replace(" ", "")

        binding.buttonKoltukSatinAl.setOnClickListener {
            //TODO: burdaki sefer id değişcek
            database.child("Seferler").child(seferID).get().addOnSuccessListener {
                val yolcuListe = it.child("koltuklar").getValue<ArrayList<YolcuModel>>()
                //kimse bilet almadıysa
                if (!it.hasChildren()) {
                    if(binding.editTextTextPersonName.text.toString() != "" || binding.editTextTextPersonSurname.text.toString() != ""){
                        val ad = binding.editTextTextPersonName.text.toString()
                        val soyad = binding.editTextTextPersonSurname.text.toString()
                        val yolcu = YolcuModel(ad, soyad, cinsiyet.toString(), koltukNo.toString().toInt())
                        val yolculuk = OtobusSeferlerModel(
                            seferID, kalkis.toString(), nereden.toString(), nereye.toString(), firmaAd.toString(),
                            ucret.toString().toInt(), sure.toString(), listOf(yolcu)
                        )

                        database.child("Seferler").child(seferID).setValue(yolculuk)
                        Toast.makeText(requireContext(), "Ekleme başarılı!", Toast.LENGTH_SHORT)
                            .show()

                        myFragmentManager = parentFragmentManager
                        fragmentTransaction = myFragmentManager.beginTransaction()
                        val eklemeBasarili = EklemeBasarili()
                        fragmentTransaction.replace(R.id.frameLayout, eklemeBasarili).commit()

                    }
                    else{
                        Toast.makeText(requireContext(), "Lütfen gerekli alanları doldurunuz!", Toast.LENGTH_SHORT)
                            .show()
                    }

                    val tc = binding.editTextTextPersonId.text.toString()
                    val telNo = binding.editTextTextPersonNo.text.toString()
                    val mail = binding.editTextTextPersonMail.text.toString()
                    val cardNo = binding.editTextTextPersonCreditNo.text.toString()
                    val cardSkt = binding.editTextTextCreditSkt.text.toString()
                    val cardCvc = binding.editTextTextCreditCvc.text.toString()
                }
                /*else{
                    //TODO: Firebase'e bilgi çekme yapılcak burda.
                }*/
                it.child("koltuklar").children.forEach { koltuk ->
                    val ad = binding.editTextTextPersonName.text.toString()
                    val soyad = binding.editTextTextPersonSurname.text.toString()
                    val yolcu = YolcuModel(ad, soyad, cinsiyet.toString(), koltukNo.toString().toInt())
                    if (yolcuListe?.contains(yolcu) == false) {
                        if (FirebaseAuth.getInstance().currentUser == null) {
                            binding.buttonKoltukSatinAl.setOnClickListener {
                                val yolcular = arrayListOf<YolcuModel>()
                                yolcular.addAll(yolcuListe)
                                yolcular.add(yolcu)
                                val yolculuk = OtobusSeferlerModel(
                                    seferID, kalkis.toString(), nereden.toString(), nereye.toString(), firmaAd.toString(),
                                    ucret.toString().toInt(), sure.toString(), yolcular
                                )
                                database.child("Seferler").child(seferID).setValue(yolculuk)
                                Toast.makeText(requireContext(), "Ekleme başarılı!", Toast.LENGTH_SHORT).show()

                                myFragmentManager = parentFragmentManager
                                fragmentTransaction = myFragmentManager.beginTransaction()
                                val eklemeBasarili = EklemeBasarili()
                                fragmentTransaction.replace(R.id.frameLayout, eklemeBasarili).commit()

                            }
                        } else{
                           //TODO: hesabı açık olan kullanıcının bilgileri alınacak.
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Bu koltuk dolu! Lütfen başka bir koltuk seçimi yapınız.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        return binding.root
    }
}