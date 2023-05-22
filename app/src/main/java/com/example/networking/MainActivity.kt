package com.example.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.networking.databinding.ActivityMainBinding
import com.example.networking.fragments.SavedImagesDirections
import com.example.networking.retrofit.Waifu
import com.example.networking.retrofit.WaifuAPI
import com.example.networking.room.AppDatabase
import com.example.networking.room.entities.RoomWaifuRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity(), SavedWaifuAdapter.itemListener {

    lateinit var binding : ActivityMainBinding;

    lateinit var navController: NavController;

    lateinit var waifuApi : WaifuAPI

    lateinit var waifuAdapter: SavedWaifuAdapter;

    lateinit var database: AppDatabase

    lateinit var roomWaifuRepository: RoomWaifuRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db").allowMainThreadQueries().build()
        roomWaifuRepository = RoomWaifuRepository(database.getWaifuDao())

        MAIN = this;

        waifuAdapter = SavedWaifuAdapter(this);

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        val interceptor = HttpLoggingInterceptor();
        interceptor.level = HttpLoggingInterceptor.Level.BODY;

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.waifu.im/").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

        waifuApi = retrofit.create(WaifuAPI::class.java)



        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }


        override fun onClick(waifu: Waifu) {
            var action = SavedImagesDirections.actionSavedImagesToImage("", waifu.url)
            navController.navigate(action);
    }
}