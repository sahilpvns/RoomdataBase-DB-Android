package com.tummoc.tummoc.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tummoc.tummoc.R
import com.tummoc.tummoc.callback.RItemListener
import com.tummoc.tummoc.databinding.HomeMenuBinding
import com.tummoc.tummoc.network.db.entity.CategoryInfo


class AdapterHomeItems(var item: ArrayList<CategoryInfo>?, private var mListener:RItemListener<CategoryInfo?>) : RecyclerView.Adapter<AdapterHomeItems.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HomeMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(item?.get(holder.adapterPosition),mListener)
        holder.itemBinding.tvFood.setOnClickListener {
            item?.get(holder.adapterPosition)?.isShow = !(item?.get(holder.adapterPosition)?.isShow ?: true)
            notifyItemChanged(holder.adapterPosition)
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(mItems: ArrayList<CategoryInfo>?) {
        item = mItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return item?.size ?: 0
    }

    inner class ViewHolder(var itemBinding: HomeMenuBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindData(item: CategoryInfo?, mListener: RItemListener<CategoryInfo?>,) {
            itemBinding.apply {
                data = item
                rvMenu.layoutManager = LinearLayoutManager(itemBinding.root.context, RecyclerView.HORIZONTAL, false)
                rvMenu.adapter = MenuAdapter(item?.items,mListener)
                itemBinding.rvMenu.isVisible = item?.isShow ?: true

                val drawable = ContextCompat.getDrawable(
                    itemBinding.root.context,
                    if (item?.isShow == true) R.drawable.arrow_down else R.drawable.arrow_up
                )
                tvFood.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)

            }

        }

    }
}