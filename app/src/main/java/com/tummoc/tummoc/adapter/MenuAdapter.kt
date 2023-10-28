package com.tummoc.tummoc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tummoc.tummoc.R
import com.tummoc.tummoc.callback.RItemListener
import com.tummoc.tummoc.databinding.MenuItemBinding
import com.tummoc.tummoc.network.db.entity.CategoryInfo
import com.tummoc.tummoc.network.db.entity.MenuInfo
import com.tummoc.tummoc.network.repo.CartRepo
import com.tummoc.tummoc.network.repo.FavRepo
import com.tummoc.tummoc.utils.ImageUtils

class MenuAdapter(var items: ArrayList<MenuInfo>?,var  mListener: RItemListener<CategoryInfo?>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MenuItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items?.get(holder.adapterPosition))
        holder.itemBinding.ivAdd.setOnClickListener {
            CartRepo().insertItem(
                items?.get(holder.adapterPosition)
            )
            Toast.makeText(holder.itemBinding.root.context, "Item Add", Toast.LENGTH_SHORT).show()
            mListener.onItemClick(null,-1,-1)
        }

        holder.itemBinding.ivFav.setOnClickListener {
            FavRepo().toggleFav( items?.get(holder.adapterPosition))
            items?.get(holder.adapterPosition)?.isFav =
                !(items?.get(holder.adapterPosition)?.isFav ?: true)
            notifyItemChanged(holder.adapterPosition)
        }


    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class ViewHolder(var itemBinding: MenuItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(menu: MenuInfo?) {
            itemBinding.menu = menu
            ImageUtils.load(itemBinding.root.context, menu?.icon, itemBinding.ivItemImg)
            val drawable = ContextCompat.getDrawable(
                itemBinding.root.context,
                if (menu?.isFav == true) R.drawable.like_item else R.drawable.favorite_border
            )
            itemBinding.ivFav.setImageDrawable(drawable)

        }
    }
}