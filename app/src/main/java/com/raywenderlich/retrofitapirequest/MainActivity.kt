package com.raywenderlich.retrofitapirequest


import android.content.ContentValues.TAG

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.retrofitapirequest.adapter.CryptoAdapter
import com.raywenderlich.retrofitapirequest.adapter.CryptoAdapterArs
import com.raywenderlich.retrofitapirequest.data.dataARS.Data

import com.raywenderlich.retrofitapirequest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var cryptoAdapter: CryptoAdapter
    private lateinit var cryptoAdapterArs: CryptoAdapterArs


    //@SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerViewArs()
        setUpRecyclerView()


        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val firstResponse =
                        withContext(Dispatchers.Default) {
                            RetrofitInstance.API.getCryptos()
                        }
                    val secondResponse =
                        withContext(Dispatchers.Default) {
                            RetrofitInstance.API.getCryptosArs()

                        }
                    runOnUiThread {
                        if (firstResponse.isSuccessful && secondResponse.isSuccessful) {

                            cryptoAdapter.cryptos = firstResponse.body()!!.data
                            cryptoAdapterArs.cryptoArs = secondResponse.body()!!.data

                            Log.e("usd", firstResponse.body()!!.data.toString())
                            Log.e("ars", secondResponse.body()!!.data.toString())

                            var arsData: List<Data> = cryptoAdapterArs.cryptoArs
                            for (i in arsData){
                                println(i.name)
                                println(i.quote.ARS.price)
                                println(i.quote.ARS.market_cap)
                                println(i.quote.ARS.volume_change_24h)
                            }

                        } else {
                            Log.e(TAG, "Response not successful")
                        }
                        binding.progressBar.isVisible = false
                    }
                }

            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
        }


    }


    private fun setUpRecyclerView() = binding.rvCryptos.apply {
        cryptoAdapter = CryptoAdapter()
        adapter = cryptoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)

    }

    private fun setUpRecyclerViewArs() = binding.rvCryptos.apply {
        cryptoAdapterArs = CryptoAdapterArs()
        adapter = cryptoAdapterArs
        layoutManager = LinearLayoutManager(this@MainActivity)
    }


}



