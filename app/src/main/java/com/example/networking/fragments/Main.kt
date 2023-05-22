package com.example.networking.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.networking.MAIN
import com.example.networking.R
import com.example.networking.databinding.ActivityMainBinding
import com.example.networking.databinding.FragmentMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Main : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var binding: FragmentMainBinding
    lateinit var spinner: Spinner

    var filterTag = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false);

        spinner = binding.spinner;
        var spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.filters_array,
            android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = this

        binding.apply {
            binding.getImageButton.setOnClickListener() {
                val action = MainDirections.actionMainToImage(filterTag, "");
                MAIN.navController.navigate(action)
            }

            binding.savedImagesButton.setOnClickListener() {
                MAIN.navController.navigate(R.id.action_main_to_savedImages)

            }

            return binding.root
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        filterTag = p0?.getItemAtPosition(p2).toString()
        Log.d("filter", filterTag)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Log.d("filter", "nothing selected")
    }

}