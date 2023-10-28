package com.tummoc.tummoc.network.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tummoc.tummoc.network.db.entity.MenuInfo
import java.lang.reflect.Type


class MenuListConverter {

    @TypeConverter
    fun fromString(value: String?): ArrayList<MenuInfo>? {
        val listType: Type = object : TypeToken<ArrayList<MenuInfo>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<MenuInfo>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}