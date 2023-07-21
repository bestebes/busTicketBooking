package com.example.beste1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beste1.adapter.OtobusAdapter
import com.example.beste1.model.OtobusBilet
import com.example.beste1.databinding.FragmentBiletBulBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BiletBulFragment : Fragment(){
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        database = Firebase.database.reference
        val binding = FragmentBiletBulBinding.inflate(layoutInflater)
        binding.recyclerViewOtobus.layoutManager = LinearLayoutManager(requireContext())

        val bundle = Bundle()

        database.child("GuncelBiletler").get().addOnSuccessListener {
            val listBiletler = arrayListOf<OtobusBilet>()

        }
        //TODO: Cardview içine yazılanlar
        //TODO: seferlerin içindeki nereden nereye özellikleri guncel biletlerden seçilene göre yazıldı.
        database.child("GuncelBiletler").get().addOnSuccessListener { value ->
            val seferList = arrayListOf<OtobusBilet>()
            value.children.forEach{ snapShot ->
                val singleValue = snapShot.getValue(OtobusBilet::class.java)
                singleValue?.let {
                    seferList.add(it)
                }
            }
            val otobusList = seferList.filter {
                it.nereden == arguments?.getString("nereden") &&
                        it.nereye == arguments?.getString("nereye")
            }.toList()
            binding.recyclerViewOtobus.adapter = OtobusAdapter(otobusList)
            binding.recyclerViewOtobus.layoutManager = LinearLayoutManager(requireContext())
        }

        /*database.child("GuncelBiletler").child("Sefer1").get().addOnSuccessListener { value ->
            val firmaAd = value.child("firmaAdi").getValue<String>().toString()
            val kalkis = value.child("kalkis").getValue<String>().toString()
            val nereden = value.child("nereden").getValue<String>().toString()
            val nereye = value.child("nereye").getValue<String>().toString()
            val sure = value.child("sure").getValue<String>().toString()
            val ucret = value.child("ucret").getValue<Int>().toString().toInt()
            val logo = value.child("firmaLogo").getValue<String>().toString()

            bundle.putString("nereden", nereden)
            bundle.putString("nereye", nereye)
            bundle.putString("kalkis", kalkis)
            bundle.putString("firmaAd", firmaAd)
            bundle.putString("sure", sure)
            bundle.putInt("ucret", ucret)

            otobusList.add(OtobusBilet(firmaAd, logo, kalkis, nereden, nereye, sure, ucret))
            binding.recyclerViewOtobus.adapter = otobusAdapter
            binding.recyclerViewOtobus.layoutManager = LinearLayoutManager(requireContext())
            }
         */

        return binding.root
    }
}
