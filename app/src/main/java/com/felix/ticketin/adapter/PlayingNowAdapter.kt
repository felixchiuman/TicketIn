package com.felix.ticketin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.felix.ticketin.databinding.ItemContentBinding
import com.felix.ticketin.model.playingnow.Result
import com.squareup.picasso.Picasso

class PlayingNowAdapter (private val onItemClick: OnClickListener) : RecyclerView.Adapter<PlayingNowAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }

    private val IMAGE_BASE ="https://image.tmdb.org/t/p/w500/"

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: List<Result>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayingNowAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemContentBinding.inflate(inflater, parent,false))
    }

    override fun onBindViewHolder(holder: PlayingNowAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let { holder.bind(data) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: Result){
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
        fun onClickItem(data: Result)
    }
}