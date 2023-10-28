package com.tummoc.tummoc.network.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tummoc.tummoc.App
import com.tummoc.tummoc.network.db.converter.MenuListConverter
import com.tummoc.tummoc.network.db.dao.CartDao
import com.tummoc.tummoc.network.db.dao.FavDao
import com.tummoc.tummoc.network.db.dao.MenuDao
import com.tummoc.tummoc.network.db.entity.CategoryInfo
import com.tummoc.tummoc.network.db.entity.UserCart
import com.tummoc.tummoc.network.db.entity.UserFav


@Database(
    entities = [CategoryInfo::class, UserCart::class, UserFav::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [MenuListConverter::class])
abstract class TummocRoomDatabase : RoomDatabase() {
    abstract fun dao(): MenuDao?
    abstract fun cartDao(): CartDao?
    abstract fun favDao(): FavDao?

    companion object {
        private var INSTANCE: TummocRoomDatabase? = null

        @Synchronized
        fun getDataBase(): TummocRoomDatabase? {
            if (INSTANCE == null) {
                INSTANCE = App.mContext?.applicationContext?.let {
                    Room.databaseBuilder(it,
                        TummocRoomDatabase::class.java, "tummoc_db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}