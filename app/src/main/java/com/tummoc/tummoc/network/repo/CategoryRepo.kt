package com.tummoc.tummoc.network.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tummoc.tummoc.network.db.TummocRoomDatabase
import com.tummoc.tummoc.network.db.entity.CategoryInfo
import com.tummoc.tummoc.utils.CoroutineUtils
import com.tummoc.tummoc.utils.LocalDataUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CategoryRepo {
    private val scope = CoroutineScope(Dispatchers.IO)

    /**
     * insert all Category list from local json
     */
    fun insetCategory() {
        try {
            scope.launch {
                withContext(CoroutineUtils.ioThread) {
                    val value = TummocRoomDatabase.getDataBase()?.dao()?.getAll()
                    if (value.isNullOrEmpty()) {
                        val allCategories = LocalDataUtils().getAllCategories()
                        for (category in allCategories ?: ArrayList()) {
                            TummocRoomDatabase.getDataBase()?.dao()?.insert(category)
                        }
                    }


                }
            }
        } catch (e: Exception) {
            Log.e("TAG", "insetUser: " + e.printStackTrace())
        }
    }

    /**
     * get all category list from category table for showing to home screen
     */
    fun getAllCategory(): MutableLiveData<ArrayList<CategoryInfo>> {
        val data: MutableLiveData<ArrayList<CategoryInfo>> = MutableLiveData()
        try {
            scope.launch {
                withContext(CoroutineUtils.ioThread) {
                    data.postValue(
                        TummocRoomDatabase.getDataBase()?.dao()?.getAll() as? ArrayList<CategoryInfo>?
                    )
                }
            }
        } catch (e: Exception) {
            data.value = ArrayList()
            Log.e("TAG", "insetUser: " + e.printStackTrace())
        }
        return data
    }
}


