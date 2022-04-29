package com.raywenderlich.retrofitapirequest

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.retrofitapirequest.adapter.CryptoAdapter
import com.raywenderlich.retrofitapirequest.adapter.CryptoAdapterARS
import com.raywenderlich.retrofitapirequest.data.ArsData.DataArs
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
    private lateinit var cryptoAdapterArs: CryptoAdapterARS


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerViewARS()
        setUpRecyclerViewUSD()

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
                            cryptoAdapterArs.cryptosArs = secondResponse.body()!!.data

                            Log.e("usd",firstResponse.body()!!.data.toString())
                            Log.e("usd",secondResponse.body()!!.data.toString())
                            val consol = cryptoAdapterArs.cryptosArs.size
                            Log.e("precio",consol.toString())
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

    private fun setUpRecyclerViewARS() = binding.rvCryptos.apply {
        cryptoAdapterArs = CryptoAdapterARS()

        adapter = cryptoAdapterArs

        layoutManager = LinearLayoutManager(this@MainActivity)
    }
    private fun setUpRecyclerViewUSD() = binding.rvCryptos.apply {
        cryptoAdapter = CryptoAdapter()
        adapter = cryptoAdapter

        layoutManager = LinearLayoutManager(this@MainActivity)
    }


}



