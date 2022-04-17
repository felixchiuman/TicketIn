package com.felix.ticketin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.felix.ticketin.databinding.ItemContentBinding
import com.felix.ticketin.model.comingsoon.ResultComingSoon
import com.squareup.picasso.Picasso

class ComingSoonAdapter (private val onItemClick: OnClickListener) : RecyclerView.Adapter<ComingSoonAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ResultComingSoon>(){
        override fun areItemsTheSame(
            oldItem: ResultComingSoon,
            newItem: ResultComingSoon
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ResultComingSoon,
            newItem: ResultComingSoon
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }

    private val IMAGE_BASE ="https://image.tmdb.org/t/p/w500/"

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: List<ResultComingSoon>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingSoonAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemContentBinding.inflate(inflater, parent,false))
    }

    override fun onBindViewHolder(holder: ComingSoonAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let { holder.bind(data) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: ResultComingSoon){
            binding.apply {
                Picasso.get().load(IMAGE_BASE+data.posterPath).fit().into(ivMovie)
                tvTitle.text = data.originalTitle
                root.setOnClickListener {
                    onItemClick.onClickItem(data)
                }
            }
        }
    }
    interface OnClickListener{
        fun onClickItem(data: ResultComingSoon)
    }
}