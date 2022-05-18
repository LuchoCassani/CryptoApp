package com.raywenderlich.retrofitapirequest


import android.content.ContentValues.TAG

import android.os.Bundle
import android.util.Log
import android.widget.Button


import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.retrofitapirequest.adapter.CryptoAdapter
import com.raywenderlich.retrofitapirequest.data.Data

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



    //@SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                             RetrofitInstance.API.getCryptosArs("ARS")
                        }
                    runOnUiThread {
                        if (firstResponse.isSuccessful && secondResponse.isSuccessful) {

                            cryptoAdapter.cryptos = firstResponse.body()?.data ?: listOf()
                            //cryptoAdapter.cryptos = secondResponse.body()?.data?: listOf()


                            Log.e("prueba de monedas", "USD ${firstResponse.body()!!}")
                            Log.e("prueba de monedas","ARS ${secondResponse.body()!!} ")

                            /*var arsData: List<Data> = cryptoAdapter.cryptos
                            for (i in arsData) {
                                println(i.name)
                                println(i.quote.ARS.price.toBigDecimal().setScale(6, RoundingMode.UP).toDouble()
                                    .toString())
                                println(i.quote.ARS.market_cap.toBigDecimal().setScale(5, RoundingMode.UP).toDouble()
                                    .toString())
                                println(i.quote.ARS.volume_change_24h.toBigDecimal().setScale(4, RoundingMode.UP).toDouble()
                                    .toString())
                            }*/

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

    /*private fun arsPrice() {
        binding.rvCryptos.adapter.
        val changePrice: TextView = findViewById(R.id.usdPrice)
        val changeVolume: TextView = findViewById(R.id.volumeChange24h)
        val changeMarketCap: TextView = findViewById(R.id.marketCap)
        val changeName: TextView = findViewById(R.id.coinDivider)
        val arsData: List<Data> = cryptoAdapterArs.cryptoArs

        for (i in arsData) {
            changePrice.text = i.quote.ARS.price.toString().toBigDecimal().setScale(3, RoundingMode.UP).toDouble()
                .toString()
            changeVolume.text = i.quote.ARS.volume_change_24h.toString().toBigDecimal().setScale(3, RoundingMode.UP).toDouble()
                .toString()
            changeMarketCap.text = i.quote.ARS.market_cap.toString().toBigDecimal().setScale(5, RoundingMode.UP).toDouble()
                .toString()
            changeName.text = "Ars Price"
            println(i)
        }


    }*/



    private fun setUpRecyclerView() = binding.rvCryptos.apply {
        cryptoAdapter = CryptoAdapter()
        adapter = cryptoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)


    }




}



