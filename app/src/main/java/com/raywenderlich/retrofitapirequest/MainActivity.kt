package com.raywenderlich.retrofitapirequest

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import  android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.retrofitapirequest.adapter.CryptoAdapter
import com.raywenderlich.retrofitapirequest.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var cryptoAdapter: CryptoAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.API.getUsdCryptos()
                RetrofitInstance.API.getArsCryptos()
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {

                cryptoAdapter.cryptos = response.body()!!.data

            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }
    }
    private fun setUpRecyclerView() = binding.rvCryptos.apply {
        cryptoAdapter = CryptoAdapter()
        adapter = cryptoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }


}



