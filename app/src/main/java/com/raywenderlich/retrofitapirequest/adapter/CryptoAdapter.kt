package com.raywenderlich.retrofitapirequest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.retrofitapirequest.data.CryptoInformation

import com.raywenderlich.retrofitapirequest.databinding.ItemTodoBinding

class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder> () {

    inner class CryptoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

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
        return CryptoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.binding.apply {
            val crypto = cryptos[position]
            cryptoName.text = crypto.quote.USD.price.toString()
            //usaPrice.text = crypto.name
            //ARSPrice.text = crypto.last_updated
            //last24hsChange.text = crypto.quote.ARS.volume_change_24h.toString()
            //ARSPrice.text = crypto.quote.ARS.market_cap.toString()


        }
    }

    override fun getItemCount() = cryptos.size

}