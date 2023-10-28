package com.tummoc.tummoc.network.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tummoc.tummoc.network.db.entity.CategoryInfo


@Dao
interface MenuDao {

    @Query("SELECT * FROM category")
    suspend fun getAll(): List<CategoryInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: CategoryInfo)

}