package com.raywenderlich.retrofitapirequest.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.retrofitapirequest.data.Data
import com.raywenderlich.retrofitapirequest.databinding.ItemCryptoBinding
import java.math.RoundingMode
import java.text.DecimalFormat


open class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {


    inner class CryptoViewHolder(val binding: ItemCryptoBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


    private val diffCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)


    var cryptos: List<Data>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(
            ItemCryptoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.binding.apply {

            val crypto = cryptos[position]
            cryptoName.text = crypto.name
            cryptoSymbol.text = crypto.symbol
            usdPrice.text = crypto.quote.USD?.let { unitSetting(it.price) }
            volumeChange24h.text = crypto.quote.USD?.let { unitSetting(it.volume_change_24h) + "%" }
            marketCap.text = crypto.quote.USD?.let { unitSetting(it.market_cap) }

            if (crypto.quote.USD?.volume_change_24h.toString() < "0") {
                volumeChange24h.setTextColor(Color.RED)
            } else {
                volumeChange24h.setTextColor(Color.GREEN)
            }

        }

    }

    private fun unitSetting(value: Double): String {
        val decimalFormat = DecimalFormat("#,###.##")
        decimalFormat.roundingMode = RoundingMode.CEILING
        return (decimalFormat.format(value))

    }


    override fun getItemCount() = cryptos.size


}









