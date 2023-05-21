package com.example.networking.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.networking.MAIN
import com.example.networking.R
import com.example.networking.databinding.FragmentImageBinding
import androidx.navigation.fragment.navArgs
import com.example.networking.retrofit.Waifu
import com.example.networking.retrofit.WaifuList
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Image : Fragment() {

    lateinit var binding: FragmentImageBinding
    private val args: ImageArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageBinding.inflate(layoutInflater);


        Log.d("passed tag", args.tag)
        if (args.tag != "")
        {
            var url = ""
            var waifuList : WaifuList
            CoroutineScope(Dispatchers.IO).launch {
                if (args.tag != "random")
                {
                    waifuList = MAIN.waifuApi.getPicByTag(args.tag)
                }
                else
                {
                    waifuList = MAIN.waifuApi.getRandomPic()
                }

                requireActivity().runOnUiThread {
                    Log.d("kek", waifuList.images[0].url.toString())
                    url = waifuList.images[0].url.toString()

                    binding.apply {
                        binding.closeButton.setOnClickListener() {
                            MAIN.navController.navigate(R.id.action_image_to_main);
                        }

                        binding.getimageButton.setOnClickListener() {
                            val action = ImageDirections.actionImageSelf(args.tag)
                            MAIN.navController.navigate(action);
                        }
                        Log.d("total kek", url)
                        if (url != "")
                        {
                            Picasso.get().load(url).into(binding.imageView)
                        }
                    }
                }
            }
        }
        return binding.root
    }

}