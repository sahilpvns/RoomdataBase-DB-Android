package com.tummoc.tummoc.viewModel

import androidx.lifecycle.ViewModel
import com.tummoc.tummoc.network.repo.CartRepo
import com.tummoc.tummoc.network.repo.CategoryRepo

open class HomeViewModel : ViewModel() {
    private var cartRepo = CartRepo()
    private var categoryRepo = CategoryRepo()

    fun addLocalData() {
        CategoryRepo().insetCategory()
    }

    fun observeCartItem() = cartRepo.observeCartItem()

    fun fetchAllCart() = cartRepo.getAllCartItem()

    fun getAllCategoryList() = categoryRepo.getAllCategory()
}