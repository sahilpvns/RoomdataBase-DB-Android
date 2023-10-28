package com.tummoc.tummoc.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tummoc.tummoc.callback.RItemListener
import com.tummoc.tummoc.databinding.ItemCartBinding
import com.tummoc.tummoc.network.db.entity.UserCart
import com.tummoc.tummoc.network.repo.CartRepo
import com.tummoc.tummoc.utils.ImageUtils

class CartAdapter(var mItems: ArrayList<UserCart>?, var mListener: RItemListener<UserCart?>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemCartBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(mItems?.get(holder.adapterPosition))

        holder.itemBinding.ivAddItem.setOnClickListener {
            CartRepo().itemPlus(
                mItems?.get(holder.adapterPosition)
            )
            mListener.onItemClick(mItems?.get(holder.adapterPosition), holder.adapterPosition, 100)
        }
        holder.itemBinding.ivRemoveItem.setOnClickListener {
            CartRepo().itemRemove(
                mItems?.get(holder.adapterPosition)
            )
            mListener.onItemClick(mItems?.get(holder.adapterPosition), holder.adapterPosition, 100)
        }
    }

    override fun getItemCount(): Int {
        return mItems?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(userCarts: java.util.ArrayList<UserCart>?) {
        mItems = userCarts
        notifyDataSetChanged()
    }

    class ViewHolder(var itemBinding: ItemCartBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(get: UserCart?) {
            itemBinding.cart = get
            ImageUtils.load(itemBinding.root.context,get?.icon,itemBinding.ivItemImg)
        }

    }
}