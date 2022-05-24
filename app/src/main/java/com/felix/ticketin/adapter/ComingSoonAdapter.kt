package com.felix.ticketin.adapter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.felix.ticketin.databinding.ItemContentBinding
import com.felix.ticketin.model.comingsoon.MovieComingSoon
import com.squareup.picasso.Picasso

class ComingSoonAdapter (private val onItemClick: OnClickListener) : RecyclerView.Adapter<ComingSoonAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<MovieComingSoon>(){
        override fun areItemsTheSame(
            oldItem: MovieComingSoon,
            newItem: MovieComingSoon
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MovieComingSoon,
            newItem: MovieComingSoon
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }

    private val IMAGE_BASE ="https://image.tmdb.org/t/p/w500/"

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: List<MovieComingSoon>?) = differ.submitList(value)

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
        fun bind(data: MovieComingSoon){
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
        fun onClickItem(data: MovieComingSoon)
    }
}