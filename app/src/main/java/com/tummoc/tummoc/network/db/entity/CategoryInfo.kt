package com.tummoc.tummoc.network.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "category")
class CategoryInfo {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("items")
    var items: ArrayList<MenuInfo>? = null

    var isShow: Boolean = true

}

open class MenuInfo {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("icon")
    var icon: String? = null

    @SerializedName("price")
    var price: Float? = null

    var isFav: Boolean = false

}
