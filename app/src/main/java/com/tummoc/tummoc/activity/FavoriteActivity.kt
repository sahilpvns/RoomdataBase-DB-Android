package com.tummoc.tummoc.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tummoc.tummoc.R
import com.tummoc.tummoc.adapter.FavoriteAdapter
import com.tummoc.tummoc.callback.RItemListener
import com.tummoc.tummoc.databinding.ActivityFavoriteBinding
import com.tummoc.tummoc.network.db.entity.UserFav
import com.tummoc.tummoc.viewModel.UserViewModel

class FavoriteActivity : BaseActivity<ActivityFavoriteBinding, UserViewModel>(),
    RItemListener<UserFav?> {

    private val mAdapter by lazy { FavoriteAdapter(ArrayList(), this@FavoriteActivity) }
    override fun provideBindingVariable() {
        mViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }


    override fun provideLayoutId() = R.layout.activity_favorite

    override fun setupView(savedInstanceState: Bundle?) {
        mBinding.apply {
            rvFavoriteItem.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavoriteItem.adapter = mAdapter
            ivBack.setOnClickListener { finish() }
        }
    }

    override fun setUpObservers() {
        mViewModel.observeFavItems()?.observe(this) {
            if (it == null) return@observe
            mAdapter.updateItems(it)
        }
    }

    override fun setUpListener() {
        mViewModel.fetchAllFav()
    }

    override fun onItemClick(t: UserFav?, position: Int, action: Int) {
        if (action == 100) {
            setUpListener()
        }
    }
}