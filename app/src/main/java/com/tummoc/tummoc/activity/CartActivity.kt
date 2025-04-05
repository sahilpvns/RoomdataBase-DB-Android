package com.tummoc.tummoc.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tummoc.tummoc.R
import com.tummoc.tummoc.adapter.CartAdapter
import com.tummoc.tummoc.callback.RItemListener
import com.tummoc.tummoc.databinding.ActivityCartBinding
import com.tummoc.tummoc.network.db.entity.UserCart
import com.tummoc.tummoc.viewModel.UserViewModel

class CartActivity : BaseActivity<ActivityCartBinding, UserViewModel>(), RItemListener<UserCart?> {
    private val mAdapter by lazy { CartAdapter(ArrayList(), this@CartActivity) }

    override fun provideBindingVariable() {
        mViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun provideLayoutId() = R.layout.activity_cart

    override fun setupView(savedInstanceState: Bundle?) {
        mBinding.apply {
            rvCartItem.layoutManager = LinearLayoutManager(this@CartActivity)
            rvCartItem.adapter = mAdapter
            ivBack.setOnClickListener { finish() }
        }
    }

    override fun setUpObservers() {
        mViewModel.observeCartItem()?.observe(this) {
            if (it == null) return@observe
            mAdapter.updateItems(it as? ArrayList<UserCart>)
            bindTotalItem()
        }
    }

    override fun setUpListener() {
        mViewModel.fetchAllCart()
    }

    private fun bindTotalItem() {
        var total = 0.0
        for (item in mAdapter.mItems ?: ArrayList()) {
            total += ((item.price ?: 0).toInt() * (item.quantity))
        }

        if (total == 0.0) {
            mBinding.apply {
                topPanel.visibility = View.GONE
                btnCheckOut.visibility = View.GONE
            }
        } else {
            mBinding.apply {
                btnCheckOut.visibility = View.VISIBLE
                topPanel.visibility = View.VISIBLE
                tvSubTotalRS.text = String.format("₹$total")
                tvTotalRs.text = String.format("₹${total - 40}")
            }
        }

    }

    override fun onItemClick(t: UserCart?, position: Int, action: Int) {
        if (action == 100) {
            setUpListener()
        }
    }
}