package com.felix.ticketin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.felix.ticketin.data.room.FavEntity
import com.felix.ticketin.databinding.ItemContentBinding
import com.squareup.picasso.Picasso


class FavAdapter (private val onClickItem: OnClickListener): RecyclerView.Adapter<FavAdapter.ViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<FavEntity>(){
        override fun areItemsTheSame(oldItem: FavEntity, newItem: FavEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavEntity, newItem: FavEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
    private val IMAGE_BASE ="https://image.tmdb.org/t/p/w500/"

    private val differ = AsyncListDiffer(this, diffCallBack)
    fun submitData(value : List<FavEntity>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemContentBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: FavAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let {
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: FavEntity){
            binding.apply {
                Picasso.get().load(IMAGE_BASE+data.image).fit().into(ivMovie)
                tvTitle.text = data.name
                root.setOnClickListener {
                    onClickItem.onClickItem(data)
                }
            }
        }
    }
    interface OnClickListener{
        fun onClickItem(data: FavEntity)
    }
}