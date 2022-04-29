package com.raywenderlich.retrofitapirequest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.retrofitapirequest.data.CryptoInformation
import com.raywenderlich.retrofitapirequest.databinding.ItemCryptoBinding



class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder> () {

    inner class CryptoViewHolder(val binding: ItemCryptoBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<CryptoInformation>(){
        override fun areItemsTheSame(oldItem: CryptoInformation, newItem: CryptoInformation): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CryptoInformation, newItem: CryptoInformation): Boolean {
             return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var cryptos:List<CryptoInformation>
    get() = differ.currentList
    set(value){differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(ItemCryptoBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.binding.apply {
            val crypto = cryptos[position]
            cryptoName.text = crypto.name
            cryptoSymbol.text= crypto.symbol
            ARSPrice.text = crypto.quote.ARS.price.toString()
            volumeChange24h.text = crypto.quote.ARS.volume_change_24h.toString()
            marketCap.text = crypto.quote.ARS.market_cap.toString()

        }

    }

    override fun getItemCount() = cryptos.size

}