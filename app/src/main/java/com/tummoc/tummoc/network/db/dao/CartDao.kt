package com.tummoc.tummoc.network.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tummoc.tummoc.network.db.entity.UserCart


@Dao
interface CartDao {

    @Query("SELECT * FROM user_cart")
    suspend fun getAllItem(): List<UserCart>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: UserCart)

    @Query("SELECT * FROM user_cart WHERE id=:id")
    suspend fun getItemByMenuId(id: Int): UserCart


    @Query("UPDATE user_cart SET quantity=:number WHERE id=:id")
    suspend fun updateQuantity(id: Int, number: Int)

    @Query("DELETE FROM user_cart WHERE id = :id")
    suspend fun deleteItem(id: Int)

}