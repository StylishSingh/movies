package com.bigstep.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bigstep.assignment.databinding.ListItemVideoBinding
import com.bumptech.glide.Glide

class VideoAdapter(private var list: MutableList<VideoResult>) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {
    lateinit var listener: OnItemClickListener

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemVideoBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("VideoAdapter.onBindViewHolder $position")
        holder.bind(list[position])
    }

    fun setItemSelectedListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding : ListItemVideoBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        lateinit var holderListener: OnItemClickListener

        init {
            binding.clParent.setOnClickListener(this)
        }

        fun bind(obj: VideoResult){
            setItemSelectedListener(listener)
            with(binding){
                tvTitle.text=obj.trackName
                Glide.with(ivThumb.context).load(obj.artworkUrl100).into(ivThumb)

            }
        }

        private fun setItemSelectedListener(listener: OnItemClickListener) {
            this.holderListener = listener
        }

        override fun onClick(v: View?) {
            holderListener.onClick(adapterPosition,"")
        }

    }

    fun updateList(list : MutableList<VideoResult>){
        this.list=list
        notifyDataSetChanged()
    }
}