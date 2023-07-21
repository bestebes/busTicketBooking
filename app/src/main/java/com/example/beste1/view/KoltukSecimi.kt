package com.example.beste1.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.beste1.model.OtobusSeferlerModel
import com.example.beste1.R
import com.example.beste1.databinding.FragmentCustomDialogBinding
import com.example.beste1.databinding.FragmentKoltukSecimiBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class KoltukSecimi : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: FragmentKoltukSecimiBinding
    lateinit var myFragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var bindingDialog: FragmentCustomDialogBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingDialog = FragmentCustomDialogBinding.inflate(LayoutInflater.from(context))
        auth = Firebase.auth
        binding = FragmentKoltukSecimiBinding.inflate(layoutInflater)
        database = Firebase.database.reference
        bindingDialog = FragmentCustomDialogBinding.inflate(layoutInflater)
        val bundle = Bundle()
        val nereden = arguments?.getString("nereden")
        val nereye = arguments?.getString("nereye")
        val kalkis = arguments?.getString("kalkis")
        val firmaAd = arguments?.getString("firmaAd")
        val sure = arguments?.getString("sure")
        val ucret = arguments?.getInt("ucret")

        fun biletBilgiGecis() {
            myFragmentManager = parentFragmentManager
            fragmentTransaction = myFragmentManager.beginTransaction()
            val otobusBiletiAlBilgiGiris = OtobusBiletiAlBilgiGiris()
            bundle.putString("nereden", nereden)
            bundle.putString("nereye", nereye)
            bundle.putString("kalkis", kalkis)
            bundle.putString("firmaAd", firmaAd)
            bundle.putString("sure", sure)
            bundle.putInt("ucret", ucret!!)

            otobusBiletiAlBilgiGiris.arguments = bundle
            fragmentTransaction.replace(R.id.frameLayout, otobusBiletiAlBilgiGiris).commit()
        }

        binding.koltuk1.setOnClickListener {
            val koltukNo = 1
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView1.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }
        binding.koltuk2.setOnClickListener {
            val koltukNo = 2
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }

                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView2.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk3.setOnClickListener {
            val koltukNo = 3
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView3.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk4.setOnClickListener {
            val koltukNo = 4
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView4.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk5.setOnClickListener {
            val koltukNo = 5
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView5.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk6.setOnClickListener {
            val koltukNo = 6
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView6.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk7.setOnClickListener {
            val koltukNo = 7
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView7.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk8.setOnClickListener {
            val koltukNo = 8
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView8.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk9.setOnClickListener {
            val koltukNo = 9
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView9.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk10.setOnClickListener {
            val koltukNo = 10
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView10.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk11.setOnClickListener {
            val koltukNo = 11
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView11.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk12.setOnClickListener {
            val koltukNo = 12
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView12.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk13.setOnClickListener {
            val koltukNo = 13
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView13.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk14.setOnClickListener {
            val koltukNo = 14
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView14.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk15.setOnClickListener {
            val koltukNo = 15
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView15.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk16.setOnClickListener {
            val koltukNo = 16
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView16.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk17.setOnClickListener {
            val koltukNo = 17
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView17.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk18.setOnClickListener {
            val koltukNo = 18
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView18.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk19.setOnClickListener {
            val koltukNo = 19
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView19.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk20.setOnClickListener {
            val koltukNo = 20
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView20.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        binding.koltuk21.setOnClickListener {
            val koltukNo = 21
            val koltukList = arrayListOf<Int>()
            var isSelected = false

            Firebase.database.reference.child("Seferler").get().addOnSuccessListener {
                val seferlerList = arrayListOf<OtobusSeferlerModel>()
                it.children.forEach { dataSnaptshot ->
                    val data = dataSnaptshot.getValue(OtobusSeferlerModel::class.java)
                    data?.let { value ->
                        seferlerList.add(value)
                    }
                }
                val otobusKontrol = seferlerList.filter {
                    it.nereden == nereden && it.nereye == nereye && it.firmaAd == firmaAd
                }
                otobusKontrol.forEach {
                    it.koltuklar.forEach { it2 ->
                        koltukList.add(it2.secilenKoltukNo!!.toInt())
                        isSelected = koltukList.contains(koltukNo)
                    }
                    if (isSelected){
                        Toast.makeText(requireContext(), "Bu koltuk dolu!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        bundle.putInt("koltukNo", koltukNo)
                        val dialog = CustomDialogFragment { value ->
                            bundle.putString("cinsiyet", value)
                            binding.buttonDevam.visibility = View.VISIBLE
                            val genderColor = if (value == "kadın") {
                                "ffff0000"
                            } else {
                                "FF14B5F4"
                            }
                            binding.textView21.setTextColor(
                                Integer.parseUnsignedInt(
                                    genderColor,
                                    16
                                )
                            )
                        }
                        dialog.show(parentFragmentManager, "customDialog")
                        binding.buttonDevam.setOnClickListener {
                            if (FirebaseAuth.getInstance().currentUser == null) {
                                biletBilgiGecis()
                            } else {
                                //TODO: Kullanıcı bilgileri giriş yapan kullanıcıdan alınacak.
                            }
                        }
                    }
                }
            }
        }

        return binding.root
    }
}