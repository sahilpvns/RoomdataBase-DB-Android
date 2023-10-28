package com.tummoc.tummoc.viewModel

import com.tummoc.tummoc.network.repo.FavRepo

class UserViewModel : HomeViewModel() {
    private var favRepo = FavRepo()
    fun observeFavItems() = favRepo.observeFavItems()

    fun fetchAllFav() = favRepo.getAllFavItems()

}