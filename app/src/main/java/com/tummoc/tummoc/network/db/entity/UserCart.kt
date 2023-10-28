package com.tummoc.tummoc.network.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_cart")
class UserCart : MenuInfo() {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("cart_id")
    var cartid: Long? = null

    @SerializedName("quantity")
    var quantity: Int = 1

}





