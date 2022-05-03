package com.raywenderlich.retrofitapirequest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.retrofitapirequest.data.dataARS.Data
import com.raywenderlich.retrofitapirequest.databinding.ItemCryptoArsBinding

class CryptoAdapterArs : RecyclerView.Adapter<CryptoAdapterArs.CryptoArsViewHolder>() {


    inner class CryptoArsViewHolder(val binding: ItemCryptoArsBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var cryptoArs: List<Data>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoArsViewHolder {
        return CryptoArsViewHolder(
            ItemCryptoArsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CryptoArsViewHolder, position: Int) {
        holder.binding.apply {
            val arsCrypto = cryptoArs[position]


        }
    }

    override fun getItemCount() = cryptoArs.size

}