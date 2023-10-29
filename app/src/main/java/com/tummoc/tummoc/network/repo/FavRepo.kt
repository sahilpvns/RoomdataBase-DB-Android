package com.tummoc.tummoc.network.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tummoc.tummoc.network.db.TummocRoomDatabase
import com.tummoc.tummoc.network.db.entity.MenuInfo
import com.tummoc.tummoc.network.db.entity.UserFav
import com.tummoc.tummoc.utils.CoroutineUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavRepo {
    val scope = CoroutineScope(Dispatchers.IO)

    /**
     * toogle the favourite item. means if item is added in favourite table then remove if not adding in table
     */
    fun toggleFav( cartItem: MenuInfo?) {
        try {
            scope.launch {
                withContext(CoroutineUtils.ioThread) {
                    val item = TummocRoomDatabase.getDataBase()?.favDao()?.getItemById(cartItem?.id ?: 0)
                    if (item == null) {
                        val userFav = UserFav().apply {
                            id = cartItem?.id
                            name = cartItem?.name
                            price = cartItem?.price
                            icon = cartItem?.icon
                        }
                        TummocRoomDatabase.getDataBase()?.favDao()?.insert(userFav)
                    } else {
                        TummocRoomDatabase.getDataBase()?.favDao()?.delete(item.id ?: 0)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("TAG", "insetUser: " + e.printStackTrace())
        }
    }

    private var _allFavItem: MutableLiveData<ArrayList<UserFav>>? = MutableLiveData()
    fun observeFavItems() = _allFavItem

    /**
     * get the all user favourite list
     */
    fun getAllFavItems() {
        try {
            scope.launch {
                withContext(CoroutineUtils.ioThread) {
                    _allFavItem?.postValue(
                        TummocRoomDatabase.getDataBase()?.favDao()
                            ?.getAllItem() as? ArrayList<UserFav>?
                    )
                }
            }
        } catch (e: Exception) {
            _allFavItem?.postValue(null)
            Log.e("TAG", "insetUser: " + e.printStackTrace())
        }
    }


}


