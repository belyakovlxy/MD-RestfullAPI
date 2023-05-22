package com.example.networking.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networking.MAIN
import com.example.networking.R
import com.example.networking.databinding.FragmentSavedImagesBinding

class SavedImages : Fragment() {

    lateinit var binding: FragmentSavedImagesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedImagesBinding.inflate(layoutInflater);

        binding.apply {
            binding.rcView.layoutManager = LinearLayoutManager(context);
            binding.rcView.adapter = MAIN.waifuAdapter;

            binding.goBackButton.setOnClickListener() {
                MAIN.navController.navigate(R.id.action_savedImages_to_main);
            }
        }

        return binding.root;
    }
}