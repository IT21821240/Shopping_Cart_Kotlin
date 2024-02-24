package com.example.myapplication_labexam3.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Shop::class], version = 1)
abstract class ShopDataBase : RoomDatabase(){
    abstract fun getShopDao():ShopDao

    companion object{
        @Volatile
        private var Instance:ShopDataBase? = null

        fun getInstance(context: Context): ShopDataBase{
            synchronized(this){
            return Instance?: Room.databaseBuilder(
                context,
                ShopDataBase::class.java,
                "shop_bd"
            ).build().also {
                Instance = it
            }
            }
        }
    }
}