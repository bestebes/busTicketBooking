package com.example.beste1.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import com.example.beste1.databinding.FragmentAraBinding
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.ArrayList

class AraFragment : Fragment() {
    private lateinit var binding: FragmentAraBinding
    private var mutableLiveData = MutableLiveData<ArrayList<String>>()
    private var sehirlerList = arrayListOf<String>()
    private lateinit var sehirlerNeredenList : ArrayAdapter<String>
    private lateinit var sehirlerNereyeList : ArrayAdapter<String>
    private var neredenIndex = 0
    private var nereyeIndex = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAraBinding.inflate(layoutInflater)

        binding.buttonGidis.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), android.R.style.Theme_Holo_Dialog, DatePickerDialog.OnDateSetListener { datePicker, yil, ay, gun ->
                binding.textViewGidis.text = "${datePicker.dayOfMonth}/${datePicker.month+1}/${datePicker.year}"
            }, year, month, day).show()

            binding.textViewGidis.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    binding.buttonBiletBul.visibility = View.VISIBLE
                }
            })
        }

        binding.buttonDonus.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), android.R.style.Theme_Holo_Dialog, DatePickerDialog.OnDateSetListener { datePicker, yil, ay, gun ->
                binding.textViewDonus.text = "${datePicker.dayOfMonth}/${datePicker.month+1}/${datePicker.year}"
            }, year, month, day).show()
        }

        FirebaseDatabase.getInstance()
            .reference
            .child("Havalimanlari")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.hasChildren()) {
                    snapshot.children.forEach { singleItem ->
                        sehirlerList.add(singleItem.getValue(String::class.java).toString())
                    }
                    mutableLiveData.value = sehirlerList
                }
            }.addOnFailureListener { e ->
                Log.d("firebaseTest", e.localizedMessage)
            }

        mutableLiveData.observe(viewLifecycleOwner) {
            val sehirler = it

            val adapterNereden =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sehirler)
            binding.spinnerNereden.adapter = adapterNereden
            binding.spinnerNereden.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(item: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    val sehirler2 = it.clone() as ArrayList<String>
                    sehirler2.remove(item?.getItemAtPosition(position))
                    val adapterNereye = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        sehirler2
                    )
                    sehirlerNeredenList = adapterNereden
                    sehirlerNereyeList = adapterNereye
                    neredenIndex = binding.spinnerNereden.selectedItemPosition

                    Log.d("Spinner Index", neredenIndex.toString())
                    Log.d("Spinner Index", nereyeIndex.toString())

                    binding.spinnerNereye.isClickable = true
                    binding.spinnerNereye.adapter = adapterNereye
                    binding.switchGidisDonus.setOnCheckedChangeListener { compoundButton, isChecked ->
                        if (isChecked) {
                            binding.buttonDonus.visibility = View.VISIBLE
                            binding.textViewDonus.visibility = View.VISIBLE
                            binding.switchGidisDonus.text = "Tek Yön"
                            binding.imageView3.visibility = View.VISIBLE
                        }
                        else {
                            binding.buttonDonus.visibility = View.INVISIBLE
                            binding.textViewDonus.visibility = View.INVISIBLE
                            binding.switchGidisDonus.text = "Gidiş-Dönüş"
                            binding.imageView3.visibility = View.INVISIBLE
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            binding.spinnerNereye.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    nereyeIndex = binding.spinnerNereye.selectedItemPosition
                    Log.d("Spinner Index", neredenIndex.toString())
                    Log.d("Spinner Index", nereyeIndex.toString())
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        }
        binding.buttonSwap.setOnClickListener {
            binding.spinnerNereden.adapter = sehirlerNereyeList
            binding.spinnerNereye.adapter = sehirlerNeredenList
            binding.spinnerNereden.setSelection(neredenIndex)
            binding.spinnerNereye.setSelection(nereyeIndex)
        }
        return binding.root
    }
}