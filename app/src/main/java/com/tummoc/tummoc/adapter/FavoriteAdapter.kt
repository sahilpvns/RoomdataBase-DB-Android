package com.tummoc.tummoc.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tummoc.tummoc.callback.RItemListener
import com.tummoc.tummoc.databinding.ItemFavoriteBinding
import com.tummoc.tummoc.network.db.entity.UserFav
import com.tummoc.tummoc.network.repo.CartRepo
import com.tummoc.tummoc.network.repo.FavRepo
import com.tummoc.tummoc.utils.ImageUtils

class FavoriteAdapter(private var mItems: ArrayList<UserFav>?, private var mListener: RItemListener<UserFav?>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var binding: ItemFavoriteBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemFavoriteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(mItems?.get(holder.adapterPosition))
        holder.itemBinding.ivFav.setOnClickListener {
            FavRepo().toggleFav(mItems?.get(holder.adapterPosition))
            mListener.onItemClick(mItems?.get(holder.adapterPosition), holder.adapterPosition, 100)
        }
        holder.itemBinding.ivAdd.setOnClickListener {
            FavRepo().toggleFav(mItems?.get(holder.adapterPosition))
            CartRepo().insertItem(mItems?.get(holder.adapterPosition))
            mListener.onItemClick(mItems?.get(holder.adapterPosition), holder.adapterPosition, 100)
        }
    }

    override fun getItemCount(): Int {
        return mItems?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: java.util.ArrayList<UserFav>?) {
        mItems = items
        notifyDataSetChanged()
    }

    class ViewHolder(var itemBinding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(item: UserFav?) {
            itemBinding.item = item
            ImageUtils.load(itemBinding.root.context,item?.icon,itemBinding.ivItemImg)
        }
    }
}