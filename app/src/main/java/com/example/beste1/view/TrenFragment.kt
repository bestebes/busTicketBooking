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
import com.example.beste1.databinding.FragmentTrenBinding
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.ArrayList

class TrenFragment : Fragment() {
    private lateinit var binding: FragmentTrenBinding
    private var mutableLiveData = MutableLiveData<ArrayList<String>>()
    private var sehirlerList = arrayListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrenBinding.inflate(layoutInflater)

        binding.buttonGidis.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), android.R.style.Theme_Holo_Dialog, DatePickerDialog.OnDateSetListener { datePicker, yil, ay, gun ->
                binding.textViewGidis.text = "${datePicker.dayOfMonth}/${datePicker.month+1}/${datePicker.year}"
            }, year, month, day).show()
        }

        binding.buttonDonus.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), android.R.style.Theme_Holo_Dialog, DatePickerDialog.OnDateSetListener { datePicker, yil, ay, gun ->
                binding.textViewDonus.text = "${datePicker.dayOfMonth}/${datePicker.month+1}/${datePicker.year}"
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

        binding.progress.visibility = View.VISIBLE
        FirebaseDatabase.getInstance()
            .reference
            .child("Tren_Gar")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.hasChildren()) {
                    snapshot.children.forEach { singleItem ->
                        sehirlerList.add(singleItem.getValue(String::class.java).toString())
                    }
                    mutableLiveData.value = sehirlerList
                    binding.progress.visibility = View.GONE
                }
            }.addOnFailureListener { e ->
                Log.d("firebaseTest", e.localizedMessage)
            }

        mutableLiveData.observe(viewLifecycleOwner) {
            val sehirler = it

            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sehirler)
            binding.spinnerNereden.adapter = adapter
            binding.spinnerNereden.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(item: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    val sehirler2 = it.clone() as ArrayList<String>
                    sehirler2.remove(item?.getItemAtPosition(position))
                    val adapterNereye = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        sehirler2
                    )
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
        }
        return binding.root
    }
}