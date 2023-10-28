package com.tummoc.tummoc.network.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tummoc.tummoc.network.db.entity.UserFav


@Dao
interface FavDao {

    @Query("SELECT * FROM user_fav")
    suspend fun getAllItem(): List<UserFav>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: UserFav)

    @Query("SELECT * FROM user_fav WHERE id=:id")
    suspend fun getItemById(id: Int): UserFav


    @Query("DELETE FROM user_fav WHERE id = :id")
    suspend fun delete(id: Int)

}