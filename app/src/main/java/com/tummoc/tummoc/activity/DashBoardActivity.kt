package com.tummoc.tummoc.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tummoc.tummoc.R
import com.tummoc.tummoc.adapter.AdapterHomeItems
import com.tummoc.tummoc.callback.RItemListener
import com.tummoc.tummoc.databinding.ActivityMainBinding
import com.tummoc.tummoc.network.db.entity.CategoryInfo
import com.tummoc.tummoc.viewModel.HomeViewModel


class DashBoardActivity : BaseActivity<ActivityMainBinding, HomeViewModel>(), RItemListener<CategoryInfo?> {

    private var mAdapterHomeItems: AdapterHomeItems? = null
    override fun provideBindingVariable() {
        mViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun provideLayoutId() = R.layout.activity_main

    override fun setupView(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        mViewModel.addLocalData()
        toolbar()
        mBinding.apply {
            rvHomeItems.layoutManager = LinearLayoutManager(this@DashBoardActivity)
            mAdapterHomeItems = AdapterHomeItems(ArrayList(), this@DashBoardActivity)
            rvHomeItems.adapter = mAdapterHomeItems
            btnCat.setOnClickListener {
                categoriesAlert()
            }
        }
    }

    override fun setUpObservers() {
        mViewModel.observeCartItem()?.observe(this) {
            if (it == null) return@observe
            mBinding.headerTitle.tvCount.isVisible = it.isNotEmpty()
            mBinding.headerTitle.tvCount.text = String.format("${it.size}")
        }
    }

    override fun setUpListener() {
        mViewModel.getAllCategoryList().observe(this) {
            if (it == null) return@observe
            mAdapterHomeItems?.updateItems(it)
        }
    }


    /**
     *
     */
    private fun toolbar() {
        mBinding.apply {
            headerTitle.headerName.text = String.format("My Store")
            headerTitle.ivCart.setOnClickListener {
                startActivity(Intent(this@DashBoardActivity, CartActivity::class.java))
            }
            headerTitle.ivFavorite.setOnClickListener {
                startActivity(Intent(this@DashBoardActivity, FavoriteActivity::class.java))
            }
        }

    }


    override fun onResume() {
        super.onResume()
        mViewModel.fetchAllCart()
    }

    override fun onItemClick(t: CategoryInfo?, position: Int, action: Int) {
        if (t == null) {
            mViewModel.fetchAllCart()
        }
    }


    private fun categoriesAlert() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as? LayoutInflater
        val view: View? = inflater?.inflate(R.layout.alertdialog_categories, null)
        val dialog = Dialog(this)
        view?.let {
            dialog.setContentView(it)
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window?.setGravity(Gravity.BOTTOM)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()
        }
    }

}
