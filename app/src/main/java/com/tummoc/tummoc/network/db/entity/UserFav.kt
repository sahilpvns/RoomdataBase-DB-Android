package com.tummoc.tummoc.network.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_fav")
class UserFav : MenuInfo() {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("fav_id")
    var favid: Long? = null

}