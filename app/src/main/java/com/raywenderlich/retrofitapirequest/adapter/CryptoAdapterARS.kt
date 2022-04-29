package com.raywenderlich.retrofitapirequest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.retrofitapirequest.data.ArsData.DataArs
import com.raywenderlich.retrofitapirequest.databinding.ItemCryptoBinding

class CryptoAdapterARS : RecyclerView.Adapter<CryptoAdapterARS.CryptoViewHolder>() {
    inner class CryptoViewHolder(val binding: ItemCryptoBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<DataArs>() {
        override fun areItemsTheSame(oldItem: DataArs, newItem: DataArs): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataArs, newItem: DataArs): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var cryptosArs: List<DataArs>
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
            val arsCryptos = cryptosArs[position]
            cryptoName.text = arsCryptos.name
            cryptoSymbol.text=arsCryptos.symbol
            ARSPrice.text = arsCryptos.quote.ARS.price.toString()
            volumeChange24h.text = arsCryptos.quote.ARS.volume_change_24h.toString()
            marketCap.text = arsCryptos.quote.ARS.market_cap.toString()
        }
    }

    override fun getItemCount(): Int = cryptosArs.size


}