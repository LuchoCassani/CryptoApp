package com.raywenderlich.retrofitapirequest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.retrofitapirequest.data.CryptoInformation

import com.raywenderlich.retrofitapirequest.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder> () {

    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val crypto = cryptos[position]
            tvUso.text = crypto.quote.ARS.price.toString()


        }
    }

    override fun getItemCount() = cryptos.size

}