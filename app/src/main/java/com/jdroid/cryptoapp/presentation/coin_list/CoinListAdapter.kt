package com.jdroid.cryptoapp.presentation.coin_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import  com.jdroid.cryptoapp.databinding.ListItemCoinsBinding
import com.jdroid.cryptoapp.domain.model.CoinModel

class CoinListAdapter : RecyclerView.Adapter<CoinListAdapter.NewsViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<CoinModel>() {
        override fun areItemsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ListItemCoinsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(private val mBinding: ListItemCoinsBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind() {
            mBinding.apply {
                val coin = differ.currentList[adapterPosition]
                val name = "${adapterPosition + 1}. ${coin.name}"
                val status = if (coin.isActive!!) "Active" else "DeActive"
                val statusColor = if (coin.isActive) Color.GREEN else Color.RED
                tvCoinName.text = name
                tvCoinStatus.text = status
                tvCoinStatus.setTextColor(statusColor)

                itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(coin)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((CoinModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (CoinModel) -> Unit) {
        onItemClickListener = listener
    }
}