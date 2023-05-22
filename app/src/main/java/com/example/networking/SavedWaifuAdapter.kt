package com.example.networking

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networking.databinding.ListItemIconBinding
import com.example.networking.retrofit.Waifu
import com.squareup.picasso.Picasso

class SavedWaifuAdapter(val listener : itemListener)  : RecyclerView.Adapter<SavedWaifuAdapter.SavedWaifuHolder>(){

    var waifuList = ArrayList(MAIN.roomWaifuRepository.getAllWaifus())

    class SavedWaifuHolder(item : View) : RecyclerView.ViewHolder(item)
    {
        val binding = ListItemIconBinding.bind(item);
        fun bind(waifu : Waifu, listener: itemListener)
        {
            binding.waifuName.text = "waifu name";
            Picasso.get().load(waifu.url).into(binding.waifuImage)

            binding.waifuCard.setOnClickListener() {
                listener.onClick(waifu);
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedWaifuHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_icon, parent, false);
        return SavedWaifuHolder(view);
    }

    override fun getItemCount(): Int {
        return waifuList.size
    }

    override fun onBindViewHolder(holder: SavedWaifuHolder, position: Int) {
        waifuList[position]?.let { holder.bind(it, listener) };
    }

    fun addWaifu(waifu : Waifu)
    {
        waifuList.add(waifu);
        MAIN.roomWaifuRepository.addWaifu(waifu)
        Log.d("list size", waifuList.size.toString())
        notifyDataSetChanged();
    }


    interface itemListener
    {
        fun onClick(waifu: Waifu)
    }
}